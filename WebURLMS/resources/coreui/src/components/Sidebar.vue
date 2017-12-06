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
                      <li class="nav-item" v-for="(childL2, index) in childL1.children">
                        <SidebarNavLink :name="childL2.name" :url="childL2.url" :icon="childL2.icon" :badge="childL2.badge"/>
                      </li>
                    </SidebarNavDropdown>
                  </template>
                  <template v-else>
                    <li class="nav-item">
                      <SidebarNavLink :name="childL1.name" :url="childL1.url" :icon="childL1.icon" :badge="childL1.badge"/>
                    </li>
                  </template>
                </template>
              </SidebarNavDropdown>
            </template>
            <template v-else>
              <li class="nav-item">
                <SidebarNavLink @click.native="isSettingsPressed(item.name)" :name="item.name" :url="item.url" :icon="item.icon" :badge="item.badge"/>
              </li>
            </template>
          </template>
        </template>
      </ul>
      <slot></slot>
    </nav>
    <SidebarFooter/>
  </div>
  <b-modal v-model="settingsModal" hide-footer title="Update Lab Settings">
      <b-form @reset="populateLabSettingsModal">
      <b-form-group id="nameGroup" label="Name">
      	<b-form-input id="name" name="name" type="text" v-model="labSettings.name"  placeholder="Enter name"></b-form-input>
      	<span class="text-danger" v-if="errors.has('name')">Please enter a valid name</span>
      </b-form-group>
      <b-form-group id="fieldGroup" label="Field">
      	<b-form-input id="field" type="text" name="field" v-model="labSettings.field" placeholder="Enter field"></b-form-input>
      	<span class="text-danger" v-if="errors.has('field')">Please enter a valid field</span>
      </b-form-group>
      <b-form-group id="dateGroup" label="Start Date">
        <b-form-input id="startDate" type="text" v-model="labSettings.startDate" readonly></b-form-input>
      </b-form-group>
      <b-form-radio-group id="activeGroup" v-model="labSettings.active" :options="activeOptions" name="activeGroup">
    </b-form-radio-group>	
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

export default {
  name: 'sidebar',
  data() {
	  return {
		settingsModal: false,
		labSettings: {
			name: '',
			field: '',
			startDate: '12/01/2017',    
			active: 'Active'
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
    SidebarNavTitle
  },
  methods: {
    handleClick (e) {
      e.preventDefault()
      e.target.parentElement.classList.toggle('open')
    },
    isSettingsPressed(name) {
    		if(name == "Settings") {
    			this.showLabSettingsModal();
    		}
    },
    populateLabSettingsModal() {
    		//Fill items
    },
    showLabSettingsModal() {
    		this.populateLabSettingsModal();
    		this.settingsModal = true;
    },
    isValidInput() {
   	 	this.$validator.validateAll();
   		
   	 	if(labSettings.name == '') {
   	 		errors.add('name');
   	 		return false;
   	 	}
   	 	if(labSettings.field == '') {
   	 		errors.add('field');
   	 		return false;
   	 	}
    },
    updateLabSettings() {
    		if (isValidInput() && !this.errors.any()) {
			  //Update lab
			  this.settingsModal = false;
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
