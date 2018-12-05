import { element, by, ElementFinder } from 'protractor';

export default class LojaComponentsPage {
  title: ElementFinder = element(by.id('loja-heading'));

  getTitle() {
    return this.title;
  }
}
