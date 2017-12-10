<template>
<div>
<div class="sidebar">
    <SidebarHeader/>
    <SidebarForm/>
    <nav class="sidebar-nav">
      <div slot="header"></div>
      <ul class="nav">
        <template v-for="(item, index) in navItems">
          <template v-if="item.title">
            <SidebarNavTitle :name="item.name" :classes="item.class" :wrapper="item.wrapper"/>
          </template>
          <template v-else-if="item.divider">
            <li class="divider"></li>
          </template>
          <template v-else>
            <template v-if="item.children">
              <!-- First level dropdown -->
              <SidebarNavDropdown :name="item.name" :url="item.url" :icon="item.icon">
                <template v-for="(childL1, index) in item.children">
                  <template v-if="childL1.children">
                    <!-- Second level dropdown -->
                    <SidebarNavDropdown :name="childL1.name" :url="childL1.url" :icon="childL1.icon">
                      <li v-if="isLinkAllowed(childL2.url, childL2.name)" class="nav-item" v-for="(childL2, index) in childL1.children">
                        <SidebarNavLink @click.native="sidebarLinkPressed(childL2.name)" :name="childL2.name" :url="childL2.url" :icon="childL2.icon" :badge="childL2.badge"/>
                      </li>
                    </SidebarNavDropdown>
                  </template>
                  <template v-else>
                    <li v-if="isLinkAllowed(childL1.url, childL1.name)" class="nav-item">
                      <SidebarNavLink @click.native="sidebarLinkPressed(childL1.name)" :name="childL1.name" :url="childL1.url" :icon="childL1.icon" :badge="childL1.badge"/>
                    </li>
                  </template>
                </template>
              </SidebarNavDropdown>
            </template>
            <template v-else>
              <li v-if="isLinkAllowed(item.url, item.name)" class="nav-item">
                <SidebarNavLink @click.native="sidebarLinkPressed(item.name)" :name="item.name" :url="item.url" :icon="item.icon" :badge="item.badge"/>
              </li>
            </template>
          </template>
        </template>
      </ul>
      <slot></slot>
    </nav>
    <SidebarFooter/>
  </div>
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
  
  <b-modal v-model="settingsModal" hide-footer title="Update Lab Settings">
      <b-form @reset="populateLabSettingsModal">
      <b-form-group id="nameGroup" label="Name">
        <div class="input-group">
         <span class="input-group-addon"><i class="icon-chemistry"></i></span>
      	<b-form-input id="name" name="name" type="text" v-model="labSettings.name"  placeholder="Enter name"></b-form-input>
      	</div>
      	<span class="text-danger" v-if="errors.has('name')">Please enter a valid name</span>
      </b-form-group>
      <b-form-group id="fieldGroup" label="Field">
      <div class="input-group">
         <span class="input-group-addon"><i class="icon-globe"></i></span>
      	<b-form-input id="field" type="text" name="field" v-model="labSettings.field" placeholder="Enter field"></b-form-input>
      	<span class="text-danger" v-if="errors.has('field')">Please enter a valid field</span>
      	</div>
      </b-form-group>
      <b-form-group id="dateGroup" label="Start Date">
        <div class="input-group">
         <span class="input-group-addon"><i class="icon-calendar"></i></span>
        <b-form-input id="startDate" type="text" v-model="labSettings.startDate" readonly></b-form-input>
        </div>
      </b-form-group>
      <b-form-radio-group id="activeGroup" v-model="labSettings.active" :options="activeOptions" name="activeGroup">
    </b-form-radio-group>	
    <div v-if="unknownLabSettingsError != ''" class="row alert alert-error"> {{ unknownLabSettingsError }} </div>
    <b-button type="button" variant="primary" @click="updateLabSettings">Save Changes</b-button>
     <b-button type="button" variant="secondary" @click="settingsModal = false">Close</b-button>
    </b-form>
  </b-modal>
</div>
</template>
<script>
import SidebarFooter from './SidebarFooter'
import SidebarForm from './SidebarForm'
import SidebarHeader from './SidebarHeader'
import SidebarNavDropdown from './SidebarNavDropdown'
import SidebarNavLink from './SidebarNavLink'
import SidebarNavTitle from './SidebarNavTitle'
import VueNumeric from 'vue-numeric'
import 'bootstrap/dist/css/bootstrap.css';
import datePicker from 'vue-bootstrap-datetimepicker';
import 'eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.css';

export default {
  name: 'sidebar',
  data() {
	  return {
		settingsModal: false,
		unknownLabSettingsError: '',
		addExpenseModal: false,
		addError: '',
		director: false,
		  staff: false,
		labSettings: {
			name: '',
			field: '',
			startDate: '',    
			active: ''
		},
		dateConfig: {
			  format: 'MM/DD/YYYY',
	          useCurrent: true
		  },
		  form: {
			  description: '',
			  amount: 0.00,
			  date: ''
		  },
		activeOptions: [
			'Active', 'Unactive'
	    ]
	  }
  },
  props: {
    navItems: {
      type: Array,
      required: true,
      default: () => []
    }
  },
  components: {
    SidebarFooter,
    SidebarForm,
    SidebarHeader,
    SidebarNavDropdown,
    SidebarNavLink,
    SidebarNavTitle,
    VueNumeric,
	  datePicker
  },
  mounted: function() {
	  this.updateLabStatus();
  },
  methods: {
    handleClick (e) {
      e.preventDefault()
      e.target.parentElement.classList.toggle('open')
    },
    updateLabStatus() {
		  axios.get('/user/info')
			.then(response => {
				if(response.data['status']) {
					if(response.data['director']) {
						this.director = true;
						this.staff = false;
					} else {
						this.director = false;
						this.staff = true;
					}
				}
			});
	  },
	  isLinkAllowed(url, name) {
		  if(this.staff) {
			  if(name == 'Expenses')
				  return true;
			  
			  if(url == '/overview')
				  return true;
			  
			  if(url == '/lab/equipment')
				  return true;
			  
			  if(url == '/lab/supplies')
				  return true;
			  
			  if(url == '/admin/summary')
				  return true;
			  
			  if(url == '/admin/weekly_progress')
				  return true;
		  }
		  
		  if(this.director) {
			  //Can see everything
			  return true;
		  }
		  
		  return false;
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
	  },
	  addExpense() {
		  if (!this.errors.any()) {
			  axios.post('/expenses/add', {
				  description: this.form.description,
				  amount: parseFloat(this.form.amount),
				  date: this.form.date
			  }).then(response => {
				  if(response.data['status']) {
					  this.closeAddExpense();
				  } else {
					  this.addError = response.data['message'];
				  }
			  });
		  }
	  },
    sidebarLinkPressed(name) {
    		if(name == "Settings") {
    			this.unknownLabSettingsError = '';
    			this.showLabSettingsModal();
    		}
    		
    		if(name == "Expenses") {
    			if(this.director) {
  				this.$router.push('/admin/expenses/');
  			} else {
  				this.showAddExpenseModal();
  			}
    		}
    },
    populateLabSettingsModal() {
	    	axios.get('/labs/info')
			.then(response => {
				if(response.data['status']) {
					this.labSettings.name = response.data['name'];
					this.labSettings.field = response.data['field'];
					this.labSettings.startDate = response.data['date'];
					this.labSettings.active = response.data['active'];
				    this.unknownLabSettingsError = '';
				} else {
					this.$router.push('/login');
				}
			});
    },
    showLabSettingsModal() {
    		this.populateLabSettingsModal();
    		this.settingsModal = true;
    },
    updateLabSettings() {
    		if (!this.errors.any()) {
    			axios.post('/labs/updateLab', {
    				newName: this.labSettings.name,
    				field: this.labSettings.field,
    				active: this.labSettings.active
    			})
    			.then(response => {
    				if(response.data['status']) {
    					this.settingsModal = false;
    				} else {
    					this.$router.push('/login');
    				}
    			});
		 } else {
  			this.unknownLabSettingsError = 'Error';
  		 }
    }
  }
}
</script>

<style lang="css">
  .nav-link {
    cursor:pointer;
  }
</style>
