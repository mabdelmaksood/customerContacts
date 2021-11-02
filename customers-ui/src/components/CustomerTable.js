import React, { Component } from 'react';
import BootstrapTable from 'react-bootstrap-table-next';
import paginationFactory from 'react-bootstrap-table2-paginator';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.css';
import 'react-bootstrap-table2-paginator/dist/react-bootstrap-table2-paginator.min.css';

export default class CustomerTable extends Component {
    constructor(props){
        super(props);
        this.state ={
            columns:[
                { dataField: 'name', text: 'Name', sort: true },
                { dataField: 'phone', text: 'Phone', sort: true },
                { dataField: 'country', text: 'Country', sort: true },
                { dataField: 'isValid', text: 'Valid', sort: true },
              ],
            defaultSorted :[{
                dataField: 'name',
                order: 'desc'
              }],
        };
    }
    pagination = paginationFactory({
        page: 1,
        sizePerPage: 10,
        lastPageText: '>>',
        firstPageText: '<<',
        nextPageText: '>',
        prePageText: '<',
        showTotal: true,
        alwaysShowAllBtns: true,
        onPageChange: function (page, sizePerPage) {
          console.log('page', page);
          console.log('sizePerPage', sizePerPage);
        },
        onSizePerPageChange: function (page, sizePerPage) {
          console.log('page', page);
          console.log('sizePerPage', sizePerPage);
        }
      });
    render() {
        return (
            <div>
                <BootstrapTable bootstrap4 keyField='id' data={this.props.customers}
                 columns={this.state.columns} defaultSorted={this.state.defaultSorted} pagination={this.pagination}/>                
            </div>
        )
    }
}
