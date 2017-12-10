<template>
<div class="animated fadeIn">
    <div class="row">
		<div class="col">
			<div v-if="director"  class="row">
				<div class="col">
					<Staff :editable=false></Staff>
				</div>
			</div>

			<div class="row">
				<div class="col">
					<Equipment :editable=false></Equipment>
				</div>
			</div>

			<div class="row">
				<div class="col">
					<Supplies :editable=false></Supplies>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col">
			<div v-if="director"  class="row">
				<div class="col">
					<Expenses :editable=false></Expenses>
				</div>
			</div>

			<div v-if="director" class="row">
				<div class="col">
					<FundingAccounts :editable=false></FundingAccounts>
				</div>
			</div>

			<div class="row">
				<div class="col">
					<WeeklyProgress :editable=false></WeeklyProgress>
				</div>
			</div>
		</div>
	</div>
</div>
</template>

<script>
import Expenses from './admin/Expenses';
import FundingAccounts from './admin/FundingAccounts';
import WeeklyProgress from './admin/WeeklyProgress';
import Equipment from './lab/Equipment';
import Staff from './lab/Staff';
import Supplies from './lab/Supplies';

export default {
  name: 'overview',
  components: {
	  Expenses,
	  FundingAccounts,
	  WeeklyProgress,
	  Equipment,
	  Staff,
	  Supplies
  },
  data() {
	  return {
		  director: false,
		  staff: false
	  }
  },
  mounted : function() {
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
  }
}
</script>
