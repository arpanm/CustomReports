import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import JmdSales from './jmd-sales';
import JmdSalesDetail from './jmd-sales-detail';
import JmdSalesUpdate from './jmd-sales-update';
import JmdSalesDeleteDialog from './jmd-sales-delete-dialog';

const JmdSalesRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<JmdSales />} />
    <Route path="new" element={<JmdSalesUpdate />} />
    <Route path=":id">
      <Route index element={<JmdSalesDetail />} />
      <Route path="edit" element={<JmdSalesUpdate />} />
      <Route path="delete" element={<JmdSalesDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default JmdSalesRoutes;
