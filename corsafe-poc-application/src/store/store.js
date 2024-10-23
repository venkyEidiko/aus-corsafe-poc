import { legacy_createStore as createStore} from 'redux'
import { applyMiddleware } from 'redux';
import {thunk} from 'redux-thunk';
import { combineReducers } from 'redux';
import registerReducer from '../reducers/registerReducer';
import loginReducer from '../reducers/loginReducer';
import productReducer from '../slice/ProductSlice';
import cartReducer from '../slice/CartSlice';
const rootReducer = combineReducers({
  register: registerReducer,
  auth: loginReducer,
  products:productReducer,
  cart:cartReducer,
  
});

const store = createStore(rootReducer, applyMiddleware(thunk));

export default store;