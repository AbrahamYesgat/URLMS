<template>
<div class="wrapper">
  <div class="animated fadeIn">
    <div class="row">
      <div class="col">
 		<div class="card">
        		<div class="card-header"> Expenses </div>
        		<div class="card-body">
	        		<div v-if="editable" class="row">
	        			<div class="col">
	        				<b-button type="button" variant="success" @click="addExpenseModal = !addExpenseModal">Add Expense</b-button>
	        			</div>
	        		</div>
        		<div class="row">
        			<div class="col">
		        		<table class="table table-striped table-hover">
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
      	<b-form-input id="description" name="description" v-model="form.description" v-validate="'required'" :class="{'input': true, 'is-danger': errors.has('description') }" placeholder="Enter short description"></b-form-input>
      	<span class="text-danger" v-if="errors.has('description')" >Please enter a short description</span>
      </b-form-group>
      <b-form-group id="amountGroup" label="Amount">
      	<vue-numeric currency="$" separator="," :empty-value="0.00"  v-bind:precision="2" id="amount" name="amount" v-model="form.amount"></vue-numeric>
      </b-form-group>
      <b-form-group id="dateGroup" label="Date">
      	<date-picker v-model="form.date" :config="dateConfig"></date-picker>
      </b-form-group>
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
		  expenses: [
			  {
				  description: 'This is a small description',
				  amount: 10.00,
				  date: Date.now()
			  }
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
  methods: {
	  resetAddExpenseModal() {
		  this.form.description = '';
		  this.form.amount = 0.0;
		  this.form.date = '';
		  this.errors.clear();
	  },
	  closeAddExpense() {
		  this.addExpenseModal = false;
		  this.resetAddExpenseModalModal();
	  },
	  addExpense() {
		  this.$validator.validateAll();
		  
		  if(this.form.description == '')
			  this.errors.add('description');
		  if(this.form.amount == '')
			  this.form.amount = 0.0;
		  
		  if (!this.errors.any()) {
			  this.expenses.push({description: this.form.description, amount: this.form.amount, date: this.form.date});
			  this.closeAddExpense();
		  }
	  }
  }
}
</script>
