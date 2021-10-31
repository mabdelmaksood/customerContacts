import { EventEmitter } from 'events';
import Axios from 'axios';
import dispatcher from '../dispachers/dispatcher';

class CustomerStore extends EventEmitter {
  initCustomers(customersList){
    console.log(customersList);
    this.customers = customersList;
    this.emit('change');
  }
  constructor() {
    super();
    this.initCustomers = this.initCustomers.bind(this);
    this.customers = [
    ];
    Axios.get('http://localhost:8080/api/readcustomers')
      .then(response => {
        this.initCustomers(response.data)
      });
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