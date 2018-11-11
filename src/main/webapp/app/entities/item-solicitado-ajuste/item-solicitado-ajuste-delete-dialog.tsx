import React from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IItemSolicitadoAjuste } from 'app/shared/model/item-solicitado-ajuste.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './item-solicitado-ajuste.reducer';

export interface IItemSolicitadoAjusteDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ItemSolicitadoAjusteDeleteDialog extends React.Component<IItemSolicitadoAjusteDeleteDialogProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  confirmDelete = event => {
    this.props.deleteEntity(this.props.itemSolicitadoAjusteEntity.id);
    this.handleClose(event);
  };

  handleClose = event => {
    event.stopPropagation();
    this.props.history.goBack();
  };

  render() {
    const { itemSolicitadoAjusteEntity } = this.props;
    return (
      <Modal isOpen toggle={this.handleClose}>
        <ModalHeader toggle={this.handleClose}>
          <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
        </ModalHeader>
        <ModalBody id="dcpdesconformidadesApp.itemSolicitadoAjuste.delete.question">
          <Translate
            contentKey="dcpdesconformidadesApp.itemSolicitadoAjuste.delete.question"
            interpolate={{ id: itemSolicitadoAjusteEntity.id }}
          >
            Are you sure you want to delete this ItemSolicitadoAjuste?
          </Translate>
        </ModalBody>
        <ModalFooter>
          <Button color="secondary" onClick={this.handleClose}>
            <FontAwesomeIcon icon="ban" />
            &nbsp;
            <Translate contentKey="entity.action.cancel">Cancel</Translate>
          </Button>
          <Button id="app-confirm-delete-itemSolicitadoAjuste" color="danger" onClick={this.confirmDelete}>
            <FontAwesomeIcon icon="trash" />
            &nbsp;
            <Translate contentKey="entity.action.delete">Delete</Translate>
          </Button>
        </ModalFooter>
      </Modal>
    );
  }
}

const mapStateToProps = ({ itemSolicitadoAjuste }: IRootState) => ({
  itemSolicitadoAjusteEntity: itemSolicitadoAjuste.entity
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ItemSolicitadoAjusteDeleteDialog);
