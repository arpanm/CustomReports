import React from 'react';
import { Translate } from 'react-jhipster';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/jmd-user">
        <Translate contentKey="global.menu.entities.jmdUser" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/jmd-sales">
        <Translate contentKey="global.menu.entities.jmdSales" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
