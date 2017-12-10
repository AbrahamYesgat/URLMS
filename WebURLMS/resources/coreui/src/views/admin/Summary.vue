<template>
<div>
  <div class="animated fadeIn">
    <div v-if="director"  class="row">
      <div class="col">
        <Expenses :editable=false></Expenses>
      </div>
    </div>
  
  <div v-if="director"  class="row">
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
</template>

<script>
import Expenses from './Expenses';
import FundingAccounts from './FundingAccounts';
import WeeklyProgress from './WeeklyProgress';

export default {
  name: 'summary',
  data() {
	  return {
		  director: false,
		  staff: false
	  }
  },
  components: {
	  Expenses,
	  FundingAccounts,
	  WeeklyProgress
  },
  mounted: function() {
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
	  }
  }
}
</script>
