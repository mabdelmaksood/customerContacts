import { EventEmitter } from 'events';
import Axios from 'axios';
import dispatcher from '../dispachers/dispatcher';

class CustomerStore extends EventEmitter {
  constructor() {
    super();
    this.initCustomers = this.initCustomers.bind(this);
    this.initCountries = this.initCountries.bind(this);

    this.customers = [
    ];

    this.countries = [
    ];

    Axios.get('http://localhost:8080/api/readcustomers')
      .then(response => {
        this.initCustomers(response.data)
      });

      Axios.get('http://localhost:8080/api/countries')
      .then(response => {
        this.initCountries(response.data)
      });
  }

  initCustomers(customersList){
    this.customers = customersList;
    this.emit('change');
  }

  initCountries(countriesList){
    this.countries = countriesList;
    this.emit('change');
  }

  createCustomer(customer) {
    this.customers.push(customer);

    this.emit('change');
  }
  updateCustomer(newCustomer){
    const newCustomers = this.customers.filter(customer => customer.id !== newCustomer.id);

    newCustomers.push(newCustomer);
    this.customers = newCustomers;

    this.emit('change');
  }
  deleteCustomer(deletedCustomer){
    const newCustomers = this.customers.filter(customer => customer.id !== deletedCustomer.id);

    this.customers = newCustomers;
    this.emit('change');
  }

  getAll() {
    return this.customers;
  }

  getCountries(){
    return this.countries;
  }

  handleActions(action) {
    // eslint-disable-next-line default-case
    switch(action.type) {
    case 'CREATE_CUSTOMER': {
      this.createCustomer(action.customer);
      break;
    }
    case 'RECEIVE_CUSTOMERS': {
      this.customers = action.customers;
      this.emit('change');
      break;
    }
    case 'DELETE_CUSTOMER':{
      this.deleteCustomer(action.customer);
          
      break;
    }
    case 'UPDATE_CUSTOMER':{
      this.updateCustomer(action.customer);
        
      break;
    }
    }
  }
}

const customerStore = new CustomerStore();

dispatcher.register(customerStore.handleActions.bind(customerStore));

export default customerStore;