import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IJmdUser } from 'app/shared/model/jmd-user.model';
import { getEntities as getJmdUsers } from 'app/entities/jmd-user/jmd-user.reducer';
import { IJmdSales } from 'app/shared/model/jmd-sales.model';
import { getEntity, updateEntity, createEntity, reset } from './jmd-sales.reducer';

export const JmdSalesUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const jmdUsers = useAppSelector(state => state.jmdUser.entities);
  const jmdSalesEntity = useAppSelector(state => state.jmdSales.entity);
  const loading = useAppSelector(state => state.jmdSales.loading);
  const updating = useAppSelector(state => state.jmdSales.updating);
  const updateSuccess = useAppSelector(state => state.jmdSales.updateSuccess);

  const handleClose = () => {
    navigate('/jmd-sales');
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
      ...jmdSalesEntity,
      ...values,
      retailer: jmdUsers.find(it => it.id.toString() === values.retailer.toString()),
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
          ...jmdSalesEntity,
          retailer: jmdSalesEntity?.retailer?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="customReportApp.jmdSales.home.createOrEditLabel" data-cy="JmdSalesCreateUpdateHeading">
            <Translate contentKey="customReportApp.jmdSales.home.createOrEditLabel">Create or edit a JmdSales</Translate>
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
                  id="jmd-sales-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('customReportApp.jmdSales.prmid')}
                id="jmd-sales-prmid"
                name="prmid"
                data-cy="prmid"
                type="text"
              />
              <ValidatedField
                label={translate('customReportApp.jmdSales.unisOrder')}
                id="jmd-sales-unisOrder"
                name="unisOrder"
                data-cy="unisOrder"
                type="text"
              />
              <ValidatedField
                label={translate('customReportApp.jmdSales.unitsDelivered')}
                id="jmd-sales-unitsDelivered"
                name="unitsDelivered"
                data-cy="unitsDelivered"
                type="text"
              />
              <ValidatedField
                label={translate('customReportApp.jmdSales.unisActivated')}
                id="jmd-sales-unisActivated"
                name="unisActivated"
                data-cy="unisActivated"
                type="text"
              />
              <ValidatedField
                label={translate('customReportApp.jmdSales.jmdMonth')}
                id="jmd-sales-jmdMonth"
                name="jmdMonth"
                data-cy="jmdMonth"
                type="text"
              />
              <ValidatedField
                label={translate('customReportApp.jmdSales.jmdYear')}
                id="jmd-sales-jmdYear"
                name="jmdYear"
                data-cy="jmdYear"
                type="text"
              />
              <ValidatedField
                label={translate('customReportApp.jmdSales.jmddate')}
                id="jmd-sales-jmddate"
                name="jmddate"
                data-cy="jmddate"
                type="text"
              />
              <ValidatedField
                label={translate('customReportApp.jmdSales.createdBy')}
                id="jmd-sales-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('customReportApp.jmdSales.createdOn')}
                id="jmd-sales-createdOn"
                name="createdOn"
                data-cy="createdOn"
                type="date"
              />
              <ValidatedField
                label={translate('customReportApp.jmdSales.updatedBy')}
                id="jmd-sales-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                label={translate('customReportApp.jmdSales.updatedOn')}
                id="jmd-sales-updatedOn"
                name="updatedOn"
                data-cy="updatedOn"
                type="date"
              />
              <ValidatedField
                id="jmd-sales-retailer"
                name="retailer"
                data-cy="retailer"
                label={translate('customReportApp.jmdSales.retailer')}
                type="select"
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/jmd-sales" replace color="info">
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

export default JmdSalesUpdate;
