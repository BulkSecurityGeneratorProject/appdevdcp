import React from 'react';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Translate, translate } from 'react-jhipster';
import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from '../header-components';

export const EntitiesMenu = props => (
  // tslint:disable-next-line:jsx-self-close
  <NavDropdown icon="th-list" name={translate('global.menu.entities.main')} id="entity-menu">
    <DropdownItem tag={Link} to="/entity/questionario">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.questionario" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/loja">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.loja" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/avaliacao">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.avaliacao" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/item-avaliacao">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.itemAvaliacao" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/item-avaliado">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.itemAvaliado" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/item-avaliado-perda-quebra-acumulados">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.itemAvaliadoPerdaQuebraAcumulados" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/grupo-itens">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.grupoItens" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/anexo-item">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.anexoItem" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/item-auditado">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.itemAuditado" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/item-solicitado-ajuste">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;
      <Translate contentKey="global.menu.entities.itemSolicitadoAjuste" />
    </DropdownItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
