import React from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvField, AvGroup, AvInput } from 'availity-reactstrap-validation';
import { Translate, ICrudGetAction, ICrudDeleteAction, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IAvaliacao } from 'app/shared/model/avaliacao.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './avaliacao.reducer';

export interface IAvaliacaoDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class AvaliacaoDeleteDialog extends React.Component<IAvaliacaoDeleteDialogProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  handleSubmit = (event, values) => {
    this.props.deleteEntity(this.props.avaliacaoEntity.id, values.motivoCancelamento);
    this.handleClose(event);
  };

  handleClose = event => {
    event.stopPropagation();
    this.props.history.goBack();
  };

  render() {
    const { avaliacaoEntity } = this.props;
    return (
      <Modal isOpen toggle={this.handleClose}>
        <AvForm onValidSubmit={this.handleSubmit}>
          <ModalHeader toggle={this.handleClose}>
            <Translate contentKey="entity.cancel.title">Confirm cancel operation</Translate>
          </ModalHeader>
          <ModalBody id="dcpdesconformidadesApp.avaliacao.cancel.question">
            <Row>
              <Col md="12">
                <Translate contentKey="dcpdesconformidadesApp.avaliacao.cancel.question" interpolate={{ id: avaliacaoEntity.id }}>
                  Are you sure you want to delete this Avaliacao?
                </Translate>
              </Col>
            </Row>
            <Row>
              <Col md="12">
                <AvGroup>
                  <Label id="motivoCancelamentoLabel" for="motivoCancelamento">
                    <Translate contentKey="dcpdesconformidadesApp.avaliacao.motivoCancelamento">Motivo Cancelamento</Translate>
                  </Label>
                  <AvField
                    id="avaliacao-motivoCancelamento"
                    type="textarea"
                    className="form-control"
                    name="motivoCancelamento"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
              </Col>
            </Row>
          </ModalBody>
          <ModalFooter>
            <Button color="secondary" onClick={this.handleClose}>
              <FontAwesomeIcon icon="ban" />
              &nbsp;
              <Translate contentKey="entity.action.back">Back</Translate>
            </Button>
            <Button id="app-confirm-delete-avaliacao" type="submit" color="danger">
              <FontAwesomeIcon icon="trash" />
              &nbsp;
              <Translate contentKey="entity.action.cancel">Cancel</Translate>
            </Button>
          </ModalFooter>
        </AvForm>
      </Modal>
    );
  }
}

const mapStateToProps = ({ avaliacao }: IRootState) => ({
  avaliacaoEntity: avaliacao.entity
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AvaliacaoDeleteDialog);
