import React, { Component } from 'react';
import * as CustomerActions from './actions/CustomerActions';
import { Redirect } from 'react-router';
import PropTypes from 'prop-types';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Joi from 'joi-browser';


const schema = Joi.object({
  name: Joi.string()
    .alphanum()
    .min(3)
    .max(30)
    .required(),

  phone: Joi.string()
    .alphanum()
    .min(3)
    .max(30)
    .required(),
});
const inputStyle = {flex: '10',
  padding: '5px'};

export class UpdateCustomer extends Component {
    state={
      name:'',
      phone:'',
      error:'',
      redirect:false,

    }

    // eslint-disable-next-line react/no-deprecated
    componentWillMount(){
      const customer = this.props.location.state.customer;
      const{name, phone} = this.props.location.state.customer;
      
      this.setState({
        name,
        phone,  
      });
    }
    onChange=(e) => {
      const statename = e.target.name;

      this.setState({[statename]:e.target.value});
    }
      onSubmit=(e) => {
        e.preventDefault();
        const { error } = schema.validate(
          {name:this.state.name,
            phone:this.state.phone});

        if(error === undefined || error === null){
          const newCustomer = {
            name:this.state.name,
            phone:this.state.phone,
          };

          CustomerActions.updateCustomer(newCustomer);
          this.setState({redirect: true});
        } else{
          const message = error.message.substring(
            error.message.indexOf('[') + 1, error.message.indexOf(']'));

          this.setState({error:message});
        }
      }
    
      render() {
        if (this.state.redirect) {
          return <Redirect push to="/" />;
        }        
      
        return (
          <Form  >
            <Form.Group controlId="formBasicEmail">
              <Form.Label >Name</Form.Label><br/>
              <input style={inputStyle} type="text" name="name" 
                placeholder="add name"  value={this.state.name}  
                onChange={this.onChange}  /> 
            </Form.Group>
            <Form.Group controlId="formBasicEmail">
              <Form.Label >Phone</Form.Label><br/>
              <input style={inputStyle} type="text" name="phone" 
                placeholder="add phone" value={this.state.phone}    
                onChange={this.onChange}      /> 
            </Form.Group>
            <Button variant="primary" as="input" 
              type="submit" value="Update" onClick={this.onSubmit} />
          </Form>
        );
      }
}

UpdateCustomer.propTypes = {
  location:PropTypes.object.isRequired,
};
export default UpdateCustomer;
