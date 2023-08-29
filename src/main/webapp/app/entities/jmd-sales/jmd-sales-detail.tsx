import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './jmd-sales.reducer';

export const JmdSalesDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const jmdSalesEntity = useAppSelector(state => state.jmdSales.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="jmdSalesDetailsHeading">
          <Translate contentKey="customReportApp.jmdSales.detail.title">JmdSales</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{jmdSalesEntity.id}</dd>
          <dt>
            <span id="prmid">
              <Translate contentKey="customReportApp.jmdSales.prmid">Prmid</Translate>
            </span>
          </dt>
          <dd>{jmdSalesEntity.prmid}</dd>
          <dt>
            <span id="unisOrder">
              <Translate contentKey="customReportApp.jmdSales.unisOrder">Unis Order</Translate>
            </span>
          </dt>
          <dd>{jmdSalesEntity.unisOrder}</dd>
          <dt>
            <span id="unitsDelivered">
              <Translate contentKey="customReportApp.jmdSales.unitsDelivered">Units Delivered</Translate>
            </span>
          </dt>
          <dd>{jmdSalesEntity.unitsDelivered}</dd>
          <dt>
            <span id="unisActivated">
              <Translate contentKey="customReportApp.jmdSales.unisActivated">Unis Activated</Translate>
            </span>
          </dt>
          <dd>{jmdSalesEntity.unisActivated}</dd>
          <dt>
            <span id="jmdMonth">
              <Translate contentKey="customReportApp.jmdSales.jmdMonth">Jmd Month</Translate>
            </span>
          </dt>
          <dd>{jmdSalesEntity.jmdMonth}</dd>
          <dt>
            <span id="jmdYear">
              <Translate contentKey="customReportApp.jmdSales.jmdYear">Jmd Year</Translate>
            </span>
          </dt>
          <dd>{jmdSalesEntity.jmdYear}</dd>
          <dt>
            <span id="jmddate">
              <Translate contentKey="customReportApp.jmdSales.jmddate">Jmddate</Translate>
            </span>
          </dt>
          <dd>{jmdSalesEntity.jmddate}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="customReportApp.jmdSales.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{jmdSalesEntity.createdBy}</dd>
          <dt>
            <span id="createdOn">
              <Translate contentKey="customReportApp.jmdSales.createdOn">Created On</Translate>
            </span>
          </dt>
          <dd>
            {jmdSalesEntity.createdOn ? <TextFormat value={jmdSalesEntity.createdOn} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="customReportApp.jmdSales.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{jmdSalesEntity.updatedBy}</dd>
          <dt>
            <span id="updatedOn">
              <Translate contentKey="customReportApp.jmdSales.updatedOn">Updated On</Translate>
            </span>
          </dt>
          <dd>
            {jmdSalesEntity.updatedOn ? <TextFormat value={jmdSalesEntity.updatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <Translate contentKey="customReportApp.jmdSales.retailer">Retailer</Translate>
          </dt>
          <dd>{jmdSalesEntity.retailer ? jmdSalesEntity.retailer.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/jmd-sales" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/jmd-sales/${jmdSalesEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default JmdSalesDetail;
