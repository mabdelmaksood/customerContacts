import React from 'react';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import Header from './components/layout/Header';
import AddCustomer from './components/AddCustomer';
import Customers from './components/Customers';
import  CustomerStore from './components/stores/CustomerStore';
import UpdateCustomer from './components/UpdateCustomer';
import Table from 'react-bootstrap/Table';
import CustomerTable from './components/CustomerTable';

class App extends React.Component {
  constructor(){
    super();
    this.getCustomers = this.getCustomers.bind(this);
    this.state = {
      customers: CustomerStore.getAll(),
    };
  }
  // eslint-disable-next-line react/no-deprecated
  componentWillMount() {
    CustomerStore.on('change', this.getCustomers );
  }
  componentWillUnmount() {
    CustomerStore.removeListener('change', this.getCustomers);
  }


  getCustomers() {
    this.setState({
      customers: CustomerStore.getAll(),
    });
  }

  
  render() {
    return (
      <div className="App">
        <Header />
          <div >
            <AddCustomer  />
            <CustomerTable customers={this.state.customers}/>
          </div>
          </div>
        
        
    );
  }
}

export default App;
