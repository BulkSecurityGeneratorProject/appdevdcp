import axios from 'axios';

import { TranslatorContext, Storage } from 'react-jhipster';
import numeral from 'numeral';
import 'numeral/locales/pt-br'; // tslint:disable-line no-submodule-imports
import moment from 'moment';
import 'moment/locale/pt-br'; // tslint:disable-line no-submodule-imports

export const ACTION_TYPES = {
  SET_LOCALE: 'locale/SET_LOCALE'
};

const initialState = {
  currentLocale: undefined
};

export type LocaleState = Readonly<typeof initialState>;

export default (state: LocaleState = initialState, action): LocaleState => {
  switch (action.type) {
    case ACTION_TYPES.SET_LOCALE:
      const currentLocale = action.locale;
      if (state.currentLocale !== currentLocale) {
        TranslatorContext.setLocale(currentLocale);
        numeral.locale(currentLocale);
        moment.locale(currentLocale);
      }
      return {
        currentLocale
      };
    default:
      return state;
  }
};

export const setLocale = locale => async dispatch => {
  if (!Object.keys(TranslatorContext.context.translations).includes(locale)) {
    const response = await axios.get(`i18n/${locale}.json?buildTimestamp=${process.env.BUILD_TIMESTAMP}`, { baseURL: '' });
    TranslatorContext.registerTranslations(locale, response.data);
  }
  dispatch({
    type: ACTION_TYPES.SET_LOCALE,
    locale
  });
};
