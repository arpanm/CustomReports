import dayjs from 'dayjs';
import { IJmdSales } from 'app/shared/model/jmd-sales.model';
import { JmdRole } from 'app/shared/model/enumerations/jmd-role.model';
import { JmdClass } from 'app/shared/model/enumerations/jmd-class.model';

export interface IJmdUser {
  id?: number;
  prmid?: number | null;
  jmdRole?: keyof typeof JmdRole | null;
  jmdClass?: keyof typeof JmdClass | null;
  name?: string | null;
  phone?: number | null;
  isActive?: boolean | null;
  createdBy?: number | null;
  createdOn?: string | null;
  updatedBy?: number | null;
  updatedOn?: string | null;
  salesData?: IJmdSales[] | null;
  retailers?: IJmdUser[] | null;
  jmdos?: IJmdUser[] | null;
}

export const defaultValue: Readonly<IJmdUser> = {
  isActive: false,
};
