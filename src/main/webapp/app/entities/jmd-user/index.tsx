import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import JmdUser from './jmd-user';
import JmdUserDetail from './jmd-user-detail';
import JmdUserUpdate from './jmd-user-update';
import JmdUserDeleteDialog from './jmd-user-delete-dialog';

const JmdUserRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<JmdUser />} />
    <Route path="new" element={<JmdUserUpdate />} />
    <Route path=":id">
      <Route index element={<JmdUserDetail />} />
      <Route path="edit" element={<JmdUserUpdate />} />
      <Route path="delete" element={<JmdUserDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default JmdUserRoutes;
