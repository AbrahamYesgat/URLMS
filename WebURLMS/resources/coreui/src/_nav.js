export default {
  items: [
    {
      name: 'Overview',
      url: '/overview',
      icon: 'icon-speedometer',
    },
    {
    	  divider:true
    },
    {
    	  name: 'Laboratory',
      url: '/lab',
      icon: 'icon-chemistry',
      children: [
    	    {
    	      name: 'Staff',
    	      url: '/lab/staff',
    	      icon: 'icon-people'
    	    },
    	    {
    	        name: 'Equipment',
    	        url: '/lab/equipment',
    	        icon: 'icon-wrench'
    	    },
    	    {
    	        name: 'Supplies',
    	        url: '/lab/supplies',
    	        icon: 'icon-paper-clip'
    	    }
      ]
    },
    {
      divider: true
    },
    {
      name: 'Administration',
    	  url: '/admin',
    	  icon: 'icon-user',
    	  children: [
	   {
	    	   name: 'Summary',
	    	   url: '/admin/summary',
	    	   icon: 'icon-grid'
	   },
	   {
	        name: 'Weekly progress',
	        url: '/admin/weekly_progress',
	        icon: 'icon-chart'
	    },
	    {
	        name: 'Expenses',
	        url: '#',
	        icon: 'icon-calculator'
	    },
	    {
	        name: 'Funding accounts',
	        url: '/admin/funding_accounts',
	        icon: 'icon-wallet'
	    }
    	  ]
    },
    {
    	  divider: true
    },
    {
	 	name: 'Settings',
	 	url: '#',
	 	icon: 'icon-settings'
	 }
  ]
}
