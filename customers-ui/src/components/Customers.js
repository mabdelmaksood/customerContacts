import React, { Component } from 'react';
import Customer from './Customer';
import PropTypes from 'prop-types';

export class Customers extends Component {
  render() {
    return this.props.Customers.map(
      (customer) => (              
            
        <Customer key={customer.id} customer={customer} />
            
      )

    );
  }
}
Customers.propTypes = {
  Customers:PropTypes.array.isRequired,
};
export default Customers;
			
		