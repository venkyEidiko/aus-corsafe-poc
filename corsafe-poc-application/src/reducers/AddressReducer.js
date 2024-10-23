const initialState = {
    address: null,
    error: null
  };
  
  const addressReducer = (state = initialState, action) => {
    switch (action.type) {
      case 'POST_ADDRESS_SUCCESS':
        return {
          ...state,
          address: action.payload,
          error: null
        };
      case 'POST_ADDRESS_FAILURE':
        return {
          ...state,
          address: null,
          error: action.payload
        };
      default:
        return state;
    }
  };
  
  export default addressReducer;
  