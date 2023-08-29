import axios from 'axios';
import { createAsyncThunk, isFulfilled, isPending, isRejected } from '@reduxjs/toolkit';
import { loadMoreDataWhenScrolled, parseHeaderForLinks } from 'react-jhipster';
import { cleanEntity } from 'app/shared/util/entity-utils';
import { IQueryParams, createEntitySlice, EntityState, serializeAxiosError } from 'app/shared/reducers/reducer.utils';
import { IJmdUser, defaultValue } from 'app/shared/model/jmd-user.model';

const initialState: EntityState<IJmdUser> = {
  loading: false,
  errorMessage: null,
  entities: [],
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

const apiUrl = 'api/jmd-users';

// Actions

export const getEntities = createAsyncThunk('jmdUser/fetch_entity_list', async ({ page, size, sort }: IQueryParams) => {
  const requestUrl = `${apiUrl}?${sort ? `page=${page}&size=${size}&sort=${sort}&` : ''}cacheBuster=${new Date().getTime()}`;
  return axios.get<IJmdUser[]>(requestUrl);
});

export const getEntity = createAsyncThunk(
  'jmdUser/fetch_entity',
  async (id: string | number) => {
    const requestUrl = `${apiUrl}/${id}`;
    return axios.get<IJmdUser>(requestUrl);
  },
  { serializeError: serializeAxiosError }
);

export const createEntity = createAsyncThunk(
  'jmdUser/create_entity',
  async (entity: IJmdUser, thunkAPI) => {
    return axios.post<IJmdUser>(apiUrl, cleanEntity(entity));
  },
  { serializeError: serializeAxiosError }
);

export const updateEntity = createAsyncThunk(
  'jmdUser/update_entity',
  async (entity: IJmdUser, thunkAPI) => {
    return axios.put<IJmdUser>(`${apiUrl}/${entity.id}`, cleanEntity(entity));
  },
  { serializeError: serializeAxiosError }
);

export const partialUpdateEntity = createAsyncThunk(
  'jmdUser/partial_update_entity',
  async (entity: IJmdUser, thunkAPI) => {
    return axios.patch<IJmdUser>(`${apiUrl}/${entity.id}`, cleanEntity(entity));
  },
  { serializeError: serializeAxiosError }
);

export const deleteEntity = createAsyncThunk(
  'jmdUser/delete_entity',
  async (id: string | number, thunkAPI) => {
    const requestUrl = `${apiUrl}/${id}`;
    return await axios.delete<IJmdUser>(requestUrl);
  },
  { serializeError: serializeAxiosError }
);

// slice

export const JmdUserSlice = createEntitySlice({
  name: 'jmdUser',
  initialState,
  extraReducers(builder) {
    builder
      .addCase(getEntity.fulfilled, (state, action) => {
        state.loading = false;
        state.entity = action.payload.data;
      })
      .addCase(deleteEntity.fulfilled, state => {
        state.updating = false;
        state.updateSuccess = true;
        state.entity = {};
      })
      .addMatcher(isFulfilled(getEntities), (state, action) => {
        const { data, headers } = action.payload;
        const links = parseHeaderForLinks(headers.link);

        return {
          ...state,
          loading: false,
          links,
          entities: loadMoreDataWhenScrolled(state.entities, data, links),
          totalItems: parseInt(headers['x-total-count'], 10),
        };
      })
      .addMatcher(isFulfilled(createEntity, updateEntity, partialUpdateEntity), (state, action) => {
        state.updating = false;
        state.loading = false;
        state.updateSuccess = true;
        state.entity = action.payload.data;
      })
      .addMatcher(isPending(getEntities, getEntity), state => {
        state.errorMessage = null;
        state.updateSuccess = false;
        state.loading = true;
      })
      .addMatcher(isPending(createEntity, updateEntity, partialUpdateEntity, deleteEntity), state => {
        state.errorMessage = null;
        state.updateSuccess = false;
        state.updating = true;
      });
  },
});

export const { reset } = JmdUserSlice.actions;

// Reducer
export default JmdUserSlice.reducer;
