<template>
  <div class="app flex-row align-items-center">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-md-8">
          <div class="card-group mb-0">
            <div class="card p-4">
              <div class="card-body text-center">
              <date-picker v-model="labSettings.startDate" :config="dateConfig"></date-picker>
                <h1>Laboratories</h1>
                <div style="min-height: 350px;">
	                <table class="table table-responsive table-striped">
	                		<thead>
	                			<tr>
	                				<th>#</th>
	                				<th>Name</th>
	                				<th>Field Of Study</th>
	                				<th>Start Date</th>
	                				<th>Active</th>
	                			</tr>
	                		</thead>
	                		<tbody>
	                			<tr>
	                				<th>1</th>
	                				<th>Testing Lab</th>
	                				<th>Secret</th>
	                				<th>12/04/2017</th>
	                				<th><i class="fa fa-check"></i></th>
	                			</tr>
	                			<tr>
	                				<th>2</th>
	                				<th>Testing Lab 2</th>
	                				<th>Secret</th>
	                				<th>12/05/2017</th>
	                				<th><i class="fa fa-close"></i></th>
	                			</tr>
	                		</tbody>
	                </table>
	             </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
//Import required dependencies 
import 'bootstrap/dist/css/bootstrap.css';

// Import this component
import datePicker from 'vue-bootstrap-datetimepicker';

// Import date picker css
import 'eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.css';

export default {
  name: 'choose_lab',
  data() {
	  return {
		  dateConfig: {
			  format: 'MM/DD/YYYY',
	          useCurrent: true
		  }
	  }
  },
  components: {
	  datePicker
  }
}
</script>
