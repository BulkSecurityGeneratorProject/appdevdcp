/* tslint:disable no-unused-expression */
import { browser, element, by, protractor } from 'protractor';

import NavBarPage from './../../page-objects/navbar-page';
import SignInPage from './../../page-objects/signin-page';
import AvaliacaoComponentsPage from './avaliacao.page-object';
import { waitUntilDisplayed, waitUntilHidden } from '../../util/utils';

const expect = chai.expect;

describe('Avaliacao e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let avaliacaoComponentsPage: AvaliacaoComponentsPage;

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

  it('should load Avaliacaos', async () => {
    await navBarPage.getEntityPage('avaliacao');
    avaliacaoComponentsPage = new AvaliacaoComponentsPage();
    expect(await avaliacaoComponentsPage.getTitle().getText()).to.match(/Avaliacaos/);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
