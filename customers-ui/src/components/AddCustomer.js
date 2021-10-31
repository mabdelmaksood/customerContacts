import React, { Component } from 'react';
import * as CustomerActions from './actions/CustomerActions';
import Button from 'react-bootstrap/Button';
import Joi from 'joi-browser';


const schema = Joi.object({
  name: Joi.string()
    .alphanum()
    .min(3)
    .max(30)
    .required(),

  phone: Joi.string()
    .min(3)
    .max(30)
    .required(),
});

const inputStyle = {flex: '10',
  padding: '5px'};


export class AddCustomer extends Component {
    state={
      name:'',
      phone:'',
      error:'',
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
        CustomerActions.createCustomer(this.state.name,
          this.state.phone);
        this.setState({
          name:'',
          phone:'',
          error:'',
        });
      } else{
        const message = error.message.substring(
          error.message.indexOf('[') + 1, error.message.indexOf(']'));
          
        this.setState({error:message});
      }
    }
    render() {
      return (
        <React.Fragment>
          <form style={{display : 'flex' }} onSubmit={this.onSubmit}>
            <input style={inputStyle} type="text" name="name"
              placeholder="add name"  value={this.state.name} 
              onChange={this.onChange} /> 
            <input style={inputStyle} type="text" name="phone" 
              placeholder="add phone" value={this.state.phone} 
              onChange={this.onChange} /> 
            <Button variant="primary" as="input" 
              type="submit" value="ADD" onClick={this.onSubmit} />
          </form>
          <p style={{fontSize :10}} 
            className="text-danger">{this.state.error}</p>
        </React.Fragment>
      );
    }
}


export default AddCustomer;
