import React, { Component } from 'react';
import PropTypes from 'prop-types';
import * as CustomerActions from './actions/CustomerActions';
import Button from 'react-bootstrap/Button';
import { withRouter } from "react-router";
  

export class Customer extends Component {
  static propTypes = {
    match: PropTypes.object.isRequired,
    location: PropTypes.object.isRequired,
    history: PropTypes.object.isRequired,
  };
  onClick(customer){
    CustomerActions.deleteCustomer(customer);
  }
  onClickUpdate(newCustomer){
    this.props.history.push({
      pathname: '/form',
      state: { customer: newCustomer },
    });
  }

  render() {
    const{id, name, phone, country, isValid} = this.props.customer;

    
    return (
           
      <tr >
        <td>{name}</td>
        <td>{phone}</td>
        <td>{country}</td>
        <td>{isValid}</td>
        <td>
          <Button variant="success" 
            onClick={this.onClickUpdate.bind(this, this.props.customer)} 
          >Update</Button>
        </td>
        <td>
          <Button variant="danger" 
            onClick={this.onClick.bind(this, this.props.customer)} >Delete </Button>
        </td>
      </tr>
            
    );
  }
}


Customer.propTypes = {
  customer:PropTypes.object.isRequired,
  history:PropTypes.object,
};
const customer = withRouter(Customer);

export default customer;
