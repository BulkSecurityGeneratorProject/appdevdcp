import { $, browser, ElementFinder } from 'protractor';
import { promise } from 'selenium-webdriver';

import BasePage from './base-component';

const selector: ElementFinder = $('#settings-form');
export default class SettingsPage extends BasePage {
  selector: ElementFinder;
  name: ElementFinder = this.selector.$('#name');
  email: ElementFinder = this.selector.$('#email');
  saveButton: ElementFinder = this.selector.$('button[type=submit]');
  title: ElementFinder = $('#settings-title');

  constructor() {
    super(selector);
    this.selector = selector;
  }

  async get() {
    await browser.get('#/account/settings');
    await this.waitUntilDisplayed();
  }

  async getTitle() {
    return this.title.getAttribute('id');
  }

  async setName(name) {
    await this.name.sendKeys(name);
  }

  async setEmail(email) {
    await this.email.sendKeys(email);
  }

  async clearEmail() {
    await this.email.clear();
  }

  async save() {
    await this.saveButton.click();
  }
}
