import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getJmdUsers } from 'app/entities/jmd-user/jmd-user.reducer';
import { IJmdUser } from 'app/shared/model/jmd-user.model';
import { JmdRole } from 'app/shared/model/enumerations/jmd-role.model';
import { JmdClass } from 'app/shared/model/enumerations/jmd-class.model';
import { getEntity, updateEntity, createEntity, reset } from './jmd-user.reducer';

export const JmdUserUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const jmdUsers = useAppSelector(state => state.jmdUser.entities);
  const jmdUserEntity = useAppSelector(state => state.jmdUser.entity);
  const loading = useAppSelector(state => state.jmdUser.loading);
  const updating = useAppSelector(state => state.jmdUser.updating);
  const updateSuccess = useAppSelector(state => state.jmdUser.updateSuccess);
  const jmdRoleValues = Object.keys(JmdRole);
  const jmdClassValues = Object.keys(JmdClass);

  const handleClose = () => {
    navigate('/jmd-user');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(id));
    }

    dispatch(getJmdUsers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...jmdUserEntity,
      ...values,
      retailers: mapIdList(values.retailers),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          jmdRole: 'Jmdo',
          jmdClass: 'AClass',
          ...jmdUserEntity,
          retailers: jmdUserEntity?.retailers?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="customReportApp.jmdUser.home.createOrEditLabel" data-cy="JmdUserCreateUpdateHeading">
            <Translate contentKey="customReportApp.jmdUser.home.createOrEditLabel">Create or edit a JmdUser</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="jmd-user-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('customReportApp.jmdUser.prmid')}
                id="jmd-user-prmid"
                name="prmid"
                data-cy="prmid"
                type="text"
              />
              <ValidatedField
                label={translate('customReportApp.jmdUser.jmdRole')}
                id="jmd-user-jmdRole"
                name="jmdRole"
                data-cy="jmdRole"
                type="select"
              >
                {jmdRoleValues.map(jmdRole => (
                  <option value={jmdRole} key={jmdRole}>
                    {translate('customReportApp.JmdRole.' + jmdRole)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('customReportApp.jmdUser.jmdClass')}
                id="jmd-user-jmdClass"
                name="jmdClass"
                data-cy="jmdClass"
                type="select"
              >
                {jmdClassValues.map(jmdClass => (
                  <option value={jmdClass} key={jmdClass}>
                    {translate('customReportApp.JmdClass.' + jmdClass)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField label={translate('customReportApp.jmdUser.name')} id="jmd-user-name" name="name" data-cy="name" type="text" />
              <ValidatedField
                label={translate('customReportApp.jmdUser.phone')}
                id="jmd-user-phone"
                name="phone"
                data-cy="phone"
                type="text"
              />
              <ValidatedField
                label={translate('customReportApp.jmdUser.isActive')}
                id="jmd-user-isActive"
                name="isActive"
                data-cy="isActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('customReportApp.jmdUser.createdBy')}
                id="jmd-user-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('customReportApp.jmdUser.createdOn')}
                id="jmd-user-createdOn"
                name="createdOn"
                data-cy="createdOn"
                type="date"
              />
              <ValidatedField
                label={translate('customReportApp.jmdUser.updatedBy')}
                id="jmd-user-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                label={translate('customReportApp.jmdUser.updatedOn')}
                id="jmd-user-updatedOn"
                name="updatedOn"
                data-cy="updatedOn"
                type="date"
              />
              <ValidatedField
                label={translate('customReportApp.jmdUser.retailer')}
                id="jmd-user-retailer"
                data-cy="retailer"
                type="select"
                multiple
                name="retailers"
              >
                <option value="" key="0" />
                {jmdUsers
                  ? jmdUsers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/jmd-user" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default JmdUserUpdate;
