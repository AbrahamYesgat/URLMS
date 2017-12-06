<template>
<div class="wrapper">
  <div class="animated fadeIn">
    <div class="row">
      <div class="col">
 		<div class="card">
        		<div class="card-header"> Reports </div>
        		<div class="card-body">
	        		<div v-if="editable" class="row">
	        			<div class="col">
	        				<b-button type="button" variant="success" @click="addReportModal = !addReportModal">Add Report</b-button>
	        			</div>
	        		</div>
        		<div class="row">
        			<div class="col">
		        		<table class="table">
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
		        					<td>{{ report.preview }}</td>
		        					<td>{{ report.author }}</td>
		        					<td v-if="editable" ><a v-on:click="viewClick(index)" v-bind:id="index" href="#">View</a></td>
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
<b-modal v-model="addReportModal" hide-footer title="Add Report">
      <b-form @reset="resetAddReportModal">
      <b-form-group id="titleGroup" label="Title">
      	<b-form-input id="title" name="title" type="text" v-model="form.title" v-validate="'required'" :class="{'input': true, 'is-danger': errors.has('title') }" placeholder="Enter title"></b-form-input>
      	<span class="text-danger" v-if="errors.has('title')">Please enter a title</span>
      </b-form-group>
      <b-form-group id="reportGroup" label="Report">
      	<b-form-textarea style="min-height:250px;" id="report" name="report" v-model="form.report" v-validate="'required'" :class="{'input': true, 'is-danger': errors.has('report') }" placeholder="Enter report"></b-form-textarea>
      	<span class="text-danger" v-if="errors.has('report')" >Please enter the report</span>
      </b-form-group>
     <b-button type="button" variant="primary" @click="addReport">Save changes</b-button>
     <b-button type="button" variant="secondary" @click="closeAddReport">Close</b-button>
	</b-form>
    </b-modal>
   <b-modal v-model="viewReportModal" hide-footer :title="view.title">
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
		  form: {
			  title: '',
			  report: ''
		  },
		  view: {
			  title: '',
			  report: ''  
		  },
		  reports: [
			  {
				  title: 'Sample report',
				  report: 'This is a report on the effectiveness of our methodology. Let\'s begin by the first days, when we were still kids in the lab. I remember, one day, the director said that blabla',
				  preview: this.returnPreview('This is a report on the effectiveness of our methodology. Let\'s begin by the first days, when we were still kids in the lab. I remember, one day, the director said that blabla'),
				  author: 'Alex'
			  }
		  ],
		  
	  }
  },
  props: {
	  editable: {
		  type: Boolean,
		  default: true
	  }
  },
  methods: {
	  returnPreview(string) {
		  return string.substring(0, 100) + '...';
	  },
	  resetAddReportModal() {
		  this.form.title = '';
		  this.form.report = '';
		  this.errors.clear();
	  },
	  closeAddReport() {
		  this.addReportModal = false;
		  this.resetAddReportModal();
	  },
	  viewClick(index) {
		  this.view.title = this.reports[index].title;
		  this.view.report = this.reports[index].report;
		  this.viewReportModal = true;
	  },
	  addReport() {
		  this.$validator.validateAll();
		  
		  if(this.form.title == '')
			  this.errors.add('title');
		  if(this.form.report == '')
			  this.errors.add('report');
		  
		  if (!this.errors.any()) {
			  this.reports.push({name: this.form.title, report: this.form.report, preview: this.returnPreview(this.form.report)});
			  this.addReportModal = false;
			  this.resetAddReportModal();
		  }
	  }
  }
}
</script>
