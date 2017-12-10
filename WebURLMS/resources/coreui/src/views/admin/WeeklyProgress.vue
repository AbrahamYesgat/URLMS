<template>
<div class="wrapper">
  <div class="animated fadeIn">
    <div class="row">
      <div class="col">
 		<div class="card">
        		<div class="card-header"><a href="/admin/weekly_progress">Reports</a></div>
        		<div class="card-body">
	        		<div v-if="editable && staff" class="row">
	        			<div class="col">
	        				<b-button type="button" variant="success" @click="showAddReportModal">Add Report</b-button>
	        			</div>
	        		</div>
        		<div class="row top5">
        			<div class="col">
		        		<table class="table table-striped table-hover" v-if="reports.length > 0">
		        			<thead>
		        				<tr>
		        					<td>#</td>
		        					<td>Title</td>
		        					<td>Preview</td>
		        					<td>Author</td>
		        					<td v-if="editable" ></td>
		        				</tr>
		        			</thead>
		        			<tbody>
		        				<tr v-for="(report, index) in reports">
		        					<td>{{ index+1 }}</td>
		        					<td>{{ report.title }}</td>
		        					<td style="text-overflow: ellipsis; white-space: nowrap; max-width: 250px; overflow: hidden;">{{ report.report }}</td>
		        					<td>{{ report.author }}</td>
		        					<td><a v-on:click="viewClick(index)" v-bind:id="index" href="#">View</a></td>
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
<b-modal v-model="addReportModal" hide-footer title="Add Report">
      <b-form @reset="resetAddReportModal">
      <b-form-group id="titleGroup" label="Title">
      <div class="input-group">
         <span class="input-group-addon"><i class="icon-chart"></i></span>
      	<b-form-input id="title" name="title" type="text" v-model="form.title" v-validate="'required'" :class="{'input': true, 'is-danger': errors.has('title') }" placeholder="Enter title"></b-form-input>
      	</div>
      	<span class="text-danger" v-if="errors.has('title')">Please enter a title</span>
      </b-form-group>
      <b-form-group id="reportGroup" label="Report">
      	<b-form-textarea style="min-height:250px;" id="report" name="report" v-model="form.report" v-validate="'required'" :class="{'input': true, 'is-danger': errors.has('report') }" placeholder="Enter report"></b-form-textarea>
      	<span class="text-danger" v-if="errors.has('report')" >Please enter the report</span>
      </b-form-group>
      <div v-if="addReportError != ''" class="row alert alert-danger"> {{ addReportError }} </div>
     <b-button type="button" variant="primary" @click="addReport">Save changes</b-button>
     <b-button type="button" variant="secondary" @click="closeAddReport">Close</b-button>
	</b-form>
    </b-modal>
   <b-modal v-model="viewReportModal" hide-footer :title="view.title">
   <p>
   By <b>{{ view.author }}</b>
   </p>
   <p>
   {{ view.report }}
   </p>
  </b-modal>
</div>
</template>

<script>
export default {
  name: 'weekly_progress',
  data() {
	  return {
		  addReportModal: false,
		  viewReportModal: false,
		  director: false,
		  staff: false,
		  addReportError: '',
		  form: {
			  title: '',
			  report: ''
		  },
		  view: {
			  title: '',
			  report: '',
			  author: ''
		  },
		  reports: [
		  ],
		  
	  }
  },
  props: {
	  editable: {
		  type: Boolean,
		  default: true
	  }
  },
  mounted : function(){
		this.populateReports();
		this.updateLabStatus();
	},
  methods: {
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
	  populateReports() {
		axios.get('/progress/get').then(response => {
			if(response.data['status']) {
				this.reports = response.data['reports'];
			}
		});  
	  },
	  showAddReportModal() {
		  this.resetAddReportModal();
		  this.addReportModal = true;
	  },
	  resetAddReportModal() {
		  this.form.title = '';
		  this.form.report = '';
		  this.addReportError = '';
		  this.errors.clear();
	  },
	  closeAddReport() {
		  this.addReportModal = false;
		  this.resetAddReportModal();
	  },
	  viewClick(index) {
		  this.view.title = this.reports[index].title;
		  this.view.author = this.reports[index].author;
		  this.view.report = this.reports[index].report;
		  this.viewReportModal = true;
	  },
	  addReport() {
		  if (!this.errors.any()) {
			  axios.post('/progress/add', {
				  title: this.form.title,
			  	  report: this.form.report
			  }).then(response => {
				  if(response.data['status']) {
					  this.populateReports();
					  this.closeAddReport();
				  } else {
					  this.addReportError = response.data['message'];
				  }
			  }); 
		  }
	  }
  }
}
</script>
