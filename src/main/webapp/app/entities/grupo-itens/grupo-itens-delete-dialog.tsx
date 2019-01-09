import React from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IGrupoItens } from 'app/shared/model/grupo-itens.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './grupo-itens.reducer';

export interface IGrupoItensDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class GrupoItensDeleteDialog extends React.Component<IGrupoItensDeleteDialogProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  confirmDelete = event => {
    this.props.deleteEntity(this.props.grupoItensEntity.id);
    this.handleClose(event);
  };

  handleClose = event => {
    event.stopPropagation();
    this.props.history.goBack();
  };

  render() {
    const { grupoItensEntity } = this.props;
    return (
      <Modal isOpen toggle={this.handleClose}>
        <ModalHeader toggle={this.handleClose}>
          <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
        </ModalHeader>
        <ModalBody id="dcpdesconformidadesApp.grupoItens.delete.question">
          <Translate contentKey="dcpdesconformidadesApp.grupoItens.delete.question" interpolate={{ nome: grupoItensEntity.nome }}>
            Are you sure you want to delete this GrupoItens?
          </Translate>
        </ModalBody>
        <ModalFooter>
          <Button color="secondary" onClick={this.handleClose}>
            <FontAwesomeIcon icon="ban" />
            &nbsp;
            <Translate contentKey="entity.action.cancel">Cancel</Translate>
          </Button>
          <Button id="app-confirm-delete-grupoItens" color="danger" onClick={this.confirmDelete}>
            <FontAwesomeIcon icon="trash" />
            &nbsp;
            <Translate contentKey="entity.action.delete">Delete</Translate>
          </Button>
        </ModalFooter>
      </Modal>
    );
  }
}

const mapStateToProps = ({ grupoItens }: IRootState) => ({
  grupoItensEntity: grupoItens.entity
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(GrupoItensDeleteDialog);
