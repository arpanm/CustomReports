import React, { useState, useEffect } from 'react';
import InfiniteScroll from 'react-infinite-scroll-component';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat, getPaginationState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities, reset } from './jmd-sales.reducer';

export const JmdSales = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getPaginationState(location, ITEMS_PER_PAGE, 'id'), location.search)
  );
  const [sorting, setSorting] = useState(false);

  const jmdSalesList = useAppSelector(state => state.jmdSales.entities);
  const loading = useAppSelector(state => state.jmdSales.loading);
  const links = useAppSelector(state => state.jmdSales.links);
  const updateSuccess = useAppSelector(state => state.jmdSales.updateSuccess);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      })
    );
  };

  const resetAll = () => {
    dispatch(reset());
    setPaginationState({
      ...paginationState,
      activePage: 1,
    });
    dispatch(getEntities({}));
  };

  useEffect(() => {
    resetAll();
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      resetAll();
    }
  }, [updateSuccess]);

  useEffect(() => {
    getAllEntities();
  }, [paginationState.activePage]);

  const handleLoadMore = () => {
    if ((window as any).pageYOffset > 0) {
      setPaginationState({
        ...paginationState,
        activePage: paginationState.activePage + 1,
      });
    }
  };

  useEffect(() => {
    if (sorting) {
      getAllEntities();
      setSorting(false);
    }
  }, [sorting]);

  const sort = p => () => {
    dispatch(reset());
    setPaginationState({
      ...paginationState,
      activePage: 1,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
    setSorting(true);
  };

  const handleSyncList = () => {
    resetAll();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = paginationState.sort;
    const order = paginationState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="jmd-sales-heading" data-cy="JmdSalesHeading">
        <Translate contentKey="customReportApp.jmdSales.home.title">Jmd Sales</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="customReportApp.jmdSales.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/jmd-sales/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="customReportApp.jmdSales.home.createLabel">Create new Jmd Sales</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        <InfiniteScroll
          dataLength={jmdSalesList ? jmdSalesList.length : 0}
          next={handleLoadMore}
          hasMore={paginationState.activePage - 1 < links.next}
          loader={<div className="loader">Loading ...</div>}
        >
          {jmdSalesList && jmdSalesList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={sort('id')}>
                    <Translate contentKey="customReportApp.jmdSales.id">ID</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                  </th>
                  <th className="hand" onClick={sort('prmid')}>
                    <Translate contentKey="customReportApp.jmdSales.prmid">Prmid</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('prmid')} />
                  </th>
                  <th className="hand" onClick={sort('unisOrder')}>
                    <Translate contentKey="customReportApp.jmdSales.unisOrder">Unis Order</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('unisOrder')} />
                  </th>
                  <th className="hand" onClick={sort('unitsDelivered')}>
                    <Translate contentKey="customReportApp.jmdSales.unitsDelivered">Units Delivered</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('unitsDelivered')} />
                  </th>
                  <th className="hand" onClick={sort('unisActivated')}>
                    <Translate contentKey="customReportApp.jmdSales.unisActivated">Unis Activated</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('unisActivated')} />
                  </th>
                  <th className="hand" onClick={sort('jmdMonth')}>
                    <Translate contentKey="customReportApp.jmdSales.jmdMonth">Jmd Month</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('jmdMonth')} />
                  </th>
                  <th className="hand" onClick={sort('jmdYear')}>
                    <Translate contentKey="customReportApp.jmdSales.jmdYear">Jmd Year</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('jmdYear')} />
                  </th>
                  <th className="hand" onClick={sort('jmddate')}>
                    <Translate contentKey="customReportApp.jmdSales.jmddate">Jmddate</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('jmddate')} />
                  </th>
                  <th className="hand" onClick={sort('createdBy')}>
                    <Translate contentKey="customReportApp.jmdSales.createdBy">Created By</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('createdBy')} />
                  </th>
                  <th className="hand" onClick={sort('createdOn')}>
                    <Translate contentKey="customReportApp.jmdSales.createdOn">Created On</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('createdOn')} />
                  </th>
                  <th className="hand" onClick={sort('updatedBy')}>
                    <Translate contentKey="customReportApp.jmdSales.updatedBy">Updated By</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('updatedBy')} />
                  </th>
                  <th className="hand" onClick={sort('updatedOn')}>
                    <Translate contentKey="customReportApp.jmdSales.updatedOn">Updated On</Translate>{' '}
                    <FontAwesomeIcon icon={getSortIconByFieldName('updatedOn')} />
                  </th>
                  <th>
                    <Translate contentKey="customReportApp.jmdSales.retailer">Retailer</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {jmdSalesList.map((jmdSales, i) => (
                  <tr key={`entity-${i}`} data-cy="entityTable">
                    <td>
                      <Button tag={Link} to={`/jmd-sales/${jmdSales.id}`} color="link" size="sm">
                        {jmdSales.id}
                      </Button>
                    </td>
                    <td>{jmdSales.prmid}</td>
                    <td>{jmdSales.unisOrder}</td>
                    <td>{jmdSales.unitsDelivered}</td>
                    <td>{jmdSales.unisActivated}</td>
                    <td>{jmdSales.jmdMonth}</td>
                    <td>{jmdSales.jmdYear}</td>
                    <td>{jmdSales.jmddate}</td>
                    <td>{jmdSales.createdBy}</td>
                    <td>
                      {jmdSales.createdOn ? <TextFormat type="date" value={jmdSales.createdOn} format={APP_LOCAL_DATE_FORMAT} /> : null}
                    </td>
                    <td>{jmdSales.updatedBy}</td>
                    <td>
                      {jmdSales.updatedOn ? <TextFormat type="date" value={jmdSales.updatedOn} format={APP_LOCAL_DATE_FORMAT} /> : null}
                    </td>
                    <td>{jmdSales.retailer ? <Link to={`/jmd-user/${jmdSales.retailer.id}`}>{jmdSales.retailer.id}</Link> : ''}</td>
                    <td className="text-end">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`/jmd-sales/${jmdSales.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`/jmd-sales/${jmdSales.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`/jmd-sales/${jmdSales.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                          <FontAwesomeIcon icon="trash" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.delete">Delete</Translate>
                          </span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            !loading && (
              <div className="alert alert-warning">
                <Translate contentKey="customReportApp.jmdSales.home.notFound">No Jmd Sales found</Translate>
              </div>
            )
          )}
        </InfiniteScroll>
      </div>
    </div>
  );
};

export default JmdSales;
