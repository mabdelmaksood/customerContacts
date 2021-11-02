import dispatcher from '../dispachers/dispatcher';
import Axios from 'axios';


export function createCustomer(name, phone) {
  const customer = {
    name,
    phone,
  };

  Axios.post('http://localhost:8080/api/createcustomer', customer)
    .then(response => {
      if(response.status === 200){
        dispatcher.dispatch({
          type: 'CREATE_CUSTOMER',
          customer:response.data,
        });
      } else{
        dispatcher.dispatch({
          type:'ADD_ERROR_HANDLE',
          name,
          phone,
        });
      }
    });
}

export function customersByCountry(selectedCountries){
  console.log(selectedCountries);
  Axios.post('http://localhost:8080/api/customersbycountry', selectedCountries)
  .then(response => {
    if(response.status === 200){
      dispatcher.dispatch({
        type: 'RECEIVE_CUSTOMERS',
        customers:response.data,
      });
    } else{
      dispatcher.dispatch({
        type:'ADD_ERROR_HANDLE',
        selectedCountries,
      });
    }
  });
}
  
export function deleteCustomer(customer) {
  const url = `http://localhost:8080/api/deletecustomer/${customer.id}`;
  Axios.delete(url)
    .then(response => {
      if(response.status === 200){
        dispatcher.dispatch({
          type: 'DELETE_CUSTOMER',
          customer,
        });
      } else{
        dispatcher.dispatch({
          type:'DELETE_ERROR_HANDLE',
          customer,
        });
      }
    });
}
export function updateCustomer(customer){
  Axios.put('http://localhost:8080/api/updatecustomer',{data: { customer}})
    .then(response => {
      if(response.status === 200){
        dispatcher.dispatch({
          type: 'UPDATE_CUSTOMER',
          customer,
        });
      } else{
        dispatcher.dispatch({
          type:'UPDATE_ERROR_HANDLE',
          customer,
        });
      }
    });
}