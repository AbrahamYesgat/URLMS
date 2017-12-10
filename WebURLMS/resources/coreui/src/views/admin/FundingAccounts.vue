<template>
<div class="wrapper">
  <div class="animated fadeIn">
    <div class="row">
      <div class="col">
 		<div class="card">
        		<div class="card-header"><a href="/admin/funding_accounts">Funding accounts</a></div>
        		<div class="card-body">
	        		<div v-if="editable" class="row">
	        			<div class="col">
	        				<b-button type="button" variant="success" @click="showAddFundingAccountModal">Add Funding Account</b-button>
	        			</div>
	        		</div>
        		<div class="row top5">
        			<div class="col">
        				<div class="alert alert-success text-center" v-if="successMessage != ''"> {{ successMessage }} </div>
		        		<table class="table table-striped table-hover" v-if="fundingAccounts > 0">
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
		        		<div class="alert alert-info text-center" v-else> None :( </div>
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
         <div class="input-group">
         <span class="input-group-addon"><i class="icon-wallet"></i></span>
      	<b-form-input id="number" name="number" type="text" v-model="form.number" v-validate="'required|numeric'" :class="{'input': true, 'is-danger': errors.has('number') }" placeholder="Enter account number"></b-form-input>
      	</div>
      	<span class="text-danger" v-if="errors.has('number')">Please enter an account number</span>
      </b-form-group>
      <b-form-group id="fundsGroup" label="Funds">
      <div class="input-group">
         <span class="input-group-addon"><i class="fa fa-usd"></i></span>
      	<vue-numeric separator="," :empty-value="0.00"  v-bind:minus="true" v-bind:precision="2" id="funds" name="funds" v-model="form.funds"></vue-numeric>
      	</div>
      </b-form-group>
      <div v-if="addFundingAccountModalError != ''" class="row alert alert-danger">{{ addFundingAccountModalError }}</div>
     <b-button type="button" variant="primary" @click="addFundingAccount">Save changes</b-button>
     <b-button type="button" variant="secondary" @click="closeAddFundingAccount">Close</b-button>
	</b-form>
    </b-modal>
    
   <b-modal v-model="modifyFundingAccountModal" hide-footer title="Modify funds">
   <b-form>
      <b-form-group id="numberGroup_2" label="Account number">
      <div class="input-group">
         <span class="input-group-addon"><i class="icon-wallet"></i></span>
      	<b-form-input id="number_2" name="number" type="text" v-model="view.number" readonly></b-form-input>
      	</div>
      </b-form-group>
      <b-form-group id="fundsGroup_2" label="Funds">
      <div class="input-group">
         <span class="input-group-addon"><i class="fa fa-usd"></i></span>
      	<vue-numeric style="padding: 6px 10px;" separator="," :empty-value="0.00"  v-bind:minus="true" v-bind:precision="2" id="funds_2" name="funds" v-model="view.funds"></vue-numeric>
      	</div>
      </b-form-group>
      <div v-if="modifyFundingAccountModalError != ''" class="alert alert-danger">{{ modifyFundingAccountModalError }}</div>
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
		  addFundingAccountModalError: '',
		  modifyFundingAccountModalError: '',
		  successMessage: '',
		  modifyFundingAccountModal: false,
		  fundingAccounts: [
		  ],
		  form: {
			  number: '',
			  funds: 0.0
		  },
		  view: {
			  number: '',
			  funds: 0.0,
		  }
	  }
  },
  props: {
	  editable: {
		  type: Boolean,
		  default: true
	  }
  },
  mounted: function() {
	  this.populateFundingAccounts();
  },
  methods: {
	  populateFundingAccounts() {
		  axios.get('/fundings/get').then(response => {
			  if(response.data['status']) {
				  this.fundingAccounts = response.data['fundings'];
			  }
		  });
	  },
	  resetAddFundingAccountModal() {
		  this.form.number = '';
		  this.addFundingAccountModalError = '';
		  this.form.funds = 0.0;
		  this.errors.clear();
	  },
	  closeAddFundingAccount() {
		  this.addFundingAccountModal = false;
		  this.resetAddFundingAccountModal();
	  },
	  showAddFundingAccountModal() {
		  this.resetAddFundingAccountModal();
		  this.addFundingAccountModal = true;
	  },
	  modifyFundingAccount() {
		  if (!this.errors.any()) {
			  axios.post('/fundings/modify', {
				  number: this.view.number,
				  funds: parseFloat(this.view.funds)
			  }).then(response => {
				 if(response.data['status']) {
					 this.populateFundingAccounts();
					 this.modifyFundingAccountModal = false;
					 this.successMessage = response.data['message'];
					 var self = this;
	  				  setTimeout(function() {
	  						self.successMessage = '';
						}, 5000); 
				 } else {
					 this.modifyFundingAccountModalError = response.data['message'];
					 var self = this;
						setTimeout(function() {
							self.modifyFundingAccountModalError = '';
						}, 2000);
				 }
			  });
		  }
	  },
	  modifyClick(index) {
		  this.view.number = this.fundingAccounts[index].number;
		  this.view.funds = this.fundingAccounts[index].funds;
		  this.modifyFundingAccountModalError = '';
		  this.modifyFundingAccountModal = true;
	  },
	  deleteClick(index) {
		  axios.post('/fundings/delete', {
			  number: this.fundingAccounts[index].number
		  }).then(response => {
			  if(response.data['status']) {
					 this.populateFundingAccounts();
					 this.closeAddFundingAccount();
					 this.successMessage = response.data['message'];
					 var self = this;
	  				  setTimeout(function() {
	  						self.successMessage = '';
						}, 5000); 
				 }
		  });
	  },
	  addFundingAccount() {
		  if (!this.errors.any()) {
			  axios.post('/fundings/add', {
				  number: this.form.number,
				  funds: parseFloat(this.form.funds)
			  }).then(response => {
				 if(response.data['status']) {
					 this.populateFundingAccounts();
					 this.closeAddFundingAccount();
					 this.successMessage = response.data['message'];
					 var self = this;
	  				  setTimeout(function() {
	  						self.successMessage = '';
						}, 5000); 
				 } else {
					 this.addFundingAccountModalError = response.data['message'];
					 var self = this;
						setTimeout(function() {
							self.addFundingAccountModalError = '';
						}, 2000);
				 }
			  });
		  }
	  }
  }
}
</script>
