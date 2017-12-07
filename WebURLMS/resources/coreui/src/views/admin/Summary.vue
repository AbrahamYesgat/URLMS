<template>
<div>
  <div class="animated fadeIn">
    <div class="row">
      <div class="col">
        <Expenses :editable=false></Expenses>
      </div>
    </div>
  
  <div class="row">
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
  components: {
	  Expenses,
	  FundingAccounts,
	  WeeklyProgress
  }
}
</script>
