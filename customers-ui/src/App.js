import React from 'react';
import Header from './components/layout/Header';
import AddCustomer from './components/AddCustomer';
import CustomerStore from './components/stores/CustomerStore';
import * as CustomerActions from './components/actions/CustomerActions';
import CustomerTable from './components/CustomerTable';
import DropdownMultiselect from "react-multiselect-dropdown-bootstrap";

class App extends React.Component {
  constructor(){
    super();
    this.getCustomers = this.getCustomers.bind(this);
    this.getCountries = this.getCountries.bind(this);
    this.setSelectedCountries = this.setSelectedCountries.bind(this);
    this.state = {
      customers: CustomerStore.getAll(),
      countries: CustomerStore.getCountries(),
      selectedCountries: [],
    };
  }
  // eslint-disable-next-line react/no-deprecated
  componentWillMount() {
    CustomerStore.on('change', this.getCustomers );
    CustomerStore.on('change', this.getCountries );
  }
  componentWillUnmount() {
    CustomerStore.removeListener('change', this.getCustomers);
    CustomerStore.removeListener('change', this.getCountries);
  }


  getCustomers() {
    this.setState({
      customers: CustomerStore.getAll(),
    });
  }

  getCountries() {
    this.setState({
      countries: CustomerStore.getCountries(),
    });
  }

  setSelectedCountries(selectedCountries){
    CustomerActions.customersByCountry(selectedCountries);
  }
  
  render() {
    const countriesOptions = this.state.countries||[].map(country=> {
      return ({key: country, label: country});
    });
    console.log(countriesOptions);
    return (
      <div className="App">
        <Header />
        <div >
          <AddCustomer  />
          <DropdownMultiselect
              options={["Cameroon", "Ethiopia", "Morocco", "Mozambique", "Uganda"]}
              name="countries"
              handleOnChange={this.setSelectedCountries}
            />
          <CustomerTable customers={this.state.customers}/>
        </div>
      </div>
        
        
    );
  }
}

export default App;
