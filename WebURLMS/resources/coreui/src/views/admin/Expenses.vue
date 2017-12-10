<template>
<div class="wrapper">
  <div class="animated fadeIn">
    <div class="row">
      <div class="col">
 		<div class="card">
        		<div class="card-header"><a href="/admin/expenses">Expenses</a></div>
        		<div class="card-body">
	        		<div v-if="editable" class="row">
	        			<div class="col">
	        				<b-button type="button" variant="success" @click="showAddExpenseModal">Add Expense</b-button>
	        			</div>
	        		</div>
        		<div class="row top5">
        			<div class="col">
		        		<table class="table table-striped table-hover" v-if="expenses.length > 0">
		        			<thead>
		        				<tr>
		        					<td>#</td>
		        					<td>Description</td>
		        					<td>Amount</td>
		        					<td>Date</td>
		        				</tr>
		        			</thead>
		        			<tbody>
		        				<tr v-for="(expense, index) in expenses">
		        					<td>{{ index+1 }}</td>
		        					<td>{{ expense.description }}</td>
		        					<td>{{ expense.amount }}$</td>
		        					<td>{{ expense.date }}</td>
		        				</tr>
		        			</tbody>
		        		</table>
		        		<div class="alert alert-primary text-center" v-else> None :( </div>
		        	</div>
	        		</div>
	        	</div>
        </div>
      </div><!--/.col-->
    </div><!--/.row-->
  </div>
 <!--  Modals -->
	<b-modal v-model="addExpenseModal" hide-footer title="Add Expense">
      <b-form @reset="resetAddExpenseModal">
      <b-form-group id="descriptionGroup" label="Description">
        <div class="input-group">
         <span class="input-group-addon"><i class="icon-calculator"></i></span>
      	<b-form-input id="description" name="description" v-model="form.description" v-validate="'required'" :class="{'input': true, 'is-danger': errors.has('description') }" placeholder="Enter short description"></b-form-input>
      	</div>
      	<span class="text-danger" v-if="errors.has('description')" >Please enter a short description</span>
      </b-form-group>
      <b-form-group id="amountGroup" label="Amount">
      	<div class="input-group">
         <span class="input-group-addon"><i class="fa fa-usd"></i></span>
      	<vue-numeric style="padding: 6px 10px;" separator="," :empty-value="0.00"  v-bind:precision="2" id="amount" name="amount" v-model="form.amount"></vue-numeric>
        </div>
      </b-form-group>
      <b-form-group id="dateGroup" label="Date">
      <div class="input-group">
         <span class="input-group-addon"><i class="icon-calendar"></i></span>
      	<date-picker v-model="form.date" :config="dateConfig"></date-picker>
      	</div>
      </b-form-group>
      <div v-if="addError != ''" class="row alert alert-danger">{{ addError }}</div>
     <b-button type="button" variant="primary" @click="addExpense">Save changes</b-button>
     <b-button type="button" variant="secondary" @click="closeAddExpense">Close</b-button>
	</b-form>
    </b-modal>
</div>
</template>

<script>
import VueNumeric from 'vue-numeric'
import 'bootstrap/dist/css/bootstrap.css';
import datePicker from 'vue-bootstrap-datetimepicker';
import 'eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.css';

export default {
  name: 'expenses',
  components: {
	  VueNumeric,
	  datePicker
  },
  data() {
	  return {
		  addExpenseModal: false,
		  addError: '',
		  expenses: [
		  ],
		  dateConfig: {
			  format: 'MM/DD/YYYY',
	          useCurrent: true
		  },
		  form: {
			  description: '',
			  amount: 0.00,
			  date: ''
		  }
	  }
  },
  props: {
	  editable: {
		  type: Boolean,
		  default: true
	  }
  },
  mounted : function(){
		this.populateExpenses();
	},
  methods: {
	  populateExpenses() {
		  axios.get('/expenses/get').then(response => {
			  if(response.data['status']) {
				  this.expenses = response.data['expenses'];
			  }
		  });  
	  },
	  showAddExpenseModal() {
		  this.resetAddExpenseModal();
		  this.addExpenseModal = true;
	  },
	  resetAddExpenseModal() {
		  this.form.description = '';
		  this.addError = '';
		  this.form.amount = 0.0;
		  this.form.date = '';
		  this.errors.clear();
	  },
	  closeAddExpense() {
		  this.addExpenseModal = false;
		  this.resetAddExpenseModal();
	  },
	  addExpense() {
		  if (!this.errors.any()) {
			  axios.post('/expenses/add', {
				  description: this.form.description,
				  amount: parseFloat(this.form.amount),
				  date: this.form.date
			  }).then(response => {
				  if(response.data['status']) {
					  this.populateExpenses();
					  this.closeAddExpense();
				  } else {
					  this.addError = response.data['message'];
				  }
			  });
		  }
	  }
  }
}
</script>
