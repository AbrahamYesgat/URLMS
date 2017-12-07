<template>
<div class="wrapper">
  <div class="animated fadeIn">
    <div class="row">
      <div class="col">
 		<div class="card">
        		<div class="card-header"> Funding accounts </div>
        		<div class="card-body">
	        		<div v-if="editable" class="row">
	        			<div class="col">
	        				<b-button type="button" variant="success" @click="addFundingAccountModal = !addFundingAccountModal">Add Funding Account</b-button>
	        			</div>
	        		</div>
        		<div class="row">
        			<div class="col">
		        		<table class="table table-striped table-hover">
		        			<thead>
		        				<tr>
		        					<td>#</td>
		        					<td>Account number</td>
		        					<td>Funds</td>
		        					<td v-if="editable"></td>
		        					<td v-if="editable"></td>
		        				</tr>
		        			</thead>
		        			<tbody>
		        				<tr v-for="(account, index) in fundingAccounts">
		        					<td>{{ index+1 }}</td>
		        					<td>{{ account.number }}</td>
		        					<td>{{ account.funds }}$</td>
		        					<td v-if="editable"><a v-on:click="modifyClick(index)" v-bind:id="index" href="#">Modify</a></td>
		        					<td v-if="editable"><a v-on:click="deleteClick(index)" v-bind:id="index" href="#">Delete</a></td>
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
	<b-modal v-model="addFundingAccountModal" hide-footer title="Add Funding Account">
      <b-form @reset="resetAddFundingAccountModal">
      <b-form-group id="numberGroup" label="Account number">
      	<b-form-input id="number" name="number" type="text" v-model="form.number" v-validate="'required|numeric'" :class="{'input': true, 'is-danger': errors.has('number') }" placeholder="Enter account number"></b-form-input>
      	<span class="text-danger" v-if="errors.has('number')">Please enter an account number</span>
      </b-form-group>
      <b-form-group id="fundsGroup" label="Funds">
      	<vue-numeric currency="$" separator="," :empty-value="0.00"  v-bind:minus="true" v-bind:precision="2" id="funds" name="funds" v-model="form.funds"></vue-numeric>
      </b-form-group>
     <b-button type="button" variant="primary" @click="addFundingAccount">Save changes</b-button>
     <b-button type="button" variant="secondary" @click="closeAddFundingAccount">Close</b-button>
	</b-form>
    </b-modal>
    
   <b-modal v-model="modifyFundingAccountModal" hide-footer title="Modify funds">
   <b-form>
      <b-form-group id="numberGroup_2" label="Account number">
      	<b-form-input id="number_2" name="number" type="text" v-model="view.number" readonly></b-form-input>
      </b-form-group>
      <b-form-group id="fundsGroup_2" label="Funds">
      	<vue-numeric currency="$" separator="," :empty-value="0.00"  v-bind:minus="true" v-bind:precision="2" id="funds_2" name="funds" v-model="view.funds"></vue-numeric>
      </b-form-group>
     <b-button type="button" variant="primary" @click="modifyFundingAccount">Save changes</b-button>
     <b-button type="button" variant="secondary" @click="modifyFundingAccountModal = false">Close</b-button>
	</b-form>
  </b-modal>
  
</div>
</template>

<script>
import VueNumeric from 'vue-numeric'
export default {
  name: 'funding_accounts',
  components: {
	  VueNumeric
  },
  data() {
	  return {
		  addFundingAccountModal: false,
		  modifyFundingAccountModal: false,
		  fundingAccounts: [
			  {
				  number: '120912',
				  funds: 10.21
			  }
		  ],
		  form: {
			  number: '',
			  funds: 0.0
		  },
		  view: {
			  number: '',
			  funds: 0.0,
			  index: 0
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
	  resetAddFundingAccountModal() {
		  this.form.number = '';
		  this.form.funds = 0.0;
		  this.errors.clear();
	  },
	  closeAddFundingAccount() {
		  this.addFundingAccountModal = false;
		  this.resetAddFundingAccountModal();
	  },
	  modifyFundingAccount() {
		  this.fundingAccounts[this.view.index].funds = this.view.funds;
		  this.modifyFundingAccountModal = false;
	  },
	  modifyClick(index) {
		  this.view.number = this.fundingAccounts[index].number;
		  this.view.funds = this.fundingAccounts[index].funds;
		  this.view.index = index;
		  this.modifyFundingAccountModal = true;
	  },
	  deleteClick(index) {
		  this.fundingAccounts.splice(index, 1);
	  },
	  addFundingAccount() {
		  this.$validator.validateAll();
		  
		  if(this.form.number == '')
			  this.errors.add('number');
		  if(this.form.funds == '')
			  this.form.funds = 0.0;
		  
		  if (!this.errors.any()) {
			  this.fundingAccounts.push({number: this.form.number, funds: this.form.funds});
			  this.closeAddFundingAccount();
		  }
	  }
  }
}
</script>
