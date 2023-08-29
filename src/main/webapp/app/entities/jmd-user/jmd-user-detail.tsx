import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './jmd-user.reducer';

export const JmdUserDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const jmdUserEntity = useAppSelector(state => state.jmdUser.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="jmdUserDetailsHeading">
          <Translate contentKey="customReportApp.jmdUser.detail.title">JmdUser</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{jmdUserEntity.id}</dd>
          <dt>
            <span id="prmid">
              <Translate contentKey="customReportApp.jmdUser.prmid">Prmid</Translate>
            </span>
          </dt>
          <dd>{jmdUserEntity.prmid}</dd>
          <dt>
            <span id="jmdRole">
              <Translate contentKey="customReportApp.jmdUser.jmdRole">Jmd Role</Translate>
            </span>
          </dt>
          <dd>{jmdUserEntity.jmdRole}</dd>
          <dt>
            <span id="jmdClass">
              <Translate contentKey="customReportApp.jmdUser.jmdClass">Jmd Class</Translate>
            </span>
          </dt>
          <dd>{jmdUserEntity.jmdClass}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="customReportApp.jmdUser.name">Name</Translate>
            </span>
          </dt>
          <dd>{jmdUserEntity.name}</dd>
          <dt>
            <span id="phone">
              <Translate contentKey="customReportApp.jmdUser.phone">Phone</Translate>
            </span>
          </dt>
          <dd>{jmdUserEntity.phone}</dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="customReportApp.jmdUser.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{jmdUserEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="customReportApp.jmdUser.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{jmdUserEntity.createdBy}</dd>
          <dt>
            <span id="createdOn">
              <Translate contentKey="customReportApp.jmdUser.createdOn">Created On</Translate>
            </span>
          </dt>
          <dd>
            {jmdUserEntity.createdOn ? <TextFormat value={jmdUserEntity.createdOn} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="customReportApp.jmdUser.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{jmdUserEntity.updatedBy}</dd>
          <dt>
            <span id="updatedOn">
              <Translate contentKey="customReportApp.jmdUser.updatedOn">Updated On</Translate>
            </span>
          </dt>
          <dd>
            {jmdUserEntity.updatedOn ? <TextFormat value={jmdUserEntity.updatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <Translate contentKey="customReportApp.jmdUser.retailer">Retailer</Translate>
          </dt>
          <dd>
            {jmdUserEntity.retailers
              ? jmdUserEntity.retailers.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {jmdUserEntity.retailers && i === jmdUserEntity.retailers.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/jmd-user" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/jmd-user/${jmdUserEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default JmdUserDetail;
