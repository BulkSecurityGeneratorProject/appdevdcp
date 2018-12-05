/* tslint:disable no-unused-expression */
import { browser, element, by } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import LojaComponentsPage from './loja.page-object';
import LojaUpdatePage from './loja-update.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('Loja e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let lojaUpdatePage: LojaUpdatePage;
  let lojaComponentsPage: LojaComponentsPage;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.waitUntilDisplayed();

    await signInPage.username.sendKeys('admin');
    await signInPage.password.sendKeys('admin');
    await signInPage.loginButton.click();
    await signInPage.waitUntilHidden();

    await waitUntilDisplayed(navBarPage.entityMenu);
  });

  it('should load Lojas', async () => {
    await navBarPage.getEntityPage('loja');
    lojaComponentsPage = new LojaComponentsPage();
    expect(await lojaComponentsPage.getTitle().getText()).to.match(/Lojas/);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
