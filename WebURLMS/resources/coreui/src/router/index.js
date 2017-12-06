import Vue from 'vue'
import Router from 'vue-router'

//Containers
import Full from '../containers/Full'

//Views
import Login from '../views/Login'
import Register from '../views/Register'
import Overview from '../views/Overview'
import ChooseLab from '../views/ChooseLab'
import LabStaff from '../views/lab/Staff'
import LabEquipment from '../views/lab/Equipment'
import LabSupplies from '../views/lab/Supplies'
import AdminSummary from '../views/admin/Summary'
import AdminWeeklyProgress from '../views/admin/WeeklyProgress'
import AdminExpenses from '../views/admin/Expenses'
import AdminFundingAccounts from '../views/admin/FundingAccounts'

//Views - Pages
import Page404 from '../views/pages/Page404'
import Page500 from '../views/pages/Page500'

Vue.use(Router)

export default new Router({
	mode: 'history',
	linkActiveClass: 'open active',
	scrollBehavior: () => ({ y: 0 }),
	routes: [
		{
			path: '/',
			redirect: '/overview',
			name: 'Home',
			component: Full,
			children: [
				{
					path: 'overview',
					name: 'Overview',
					component: Overview
				},
				{
					path: '/lab',
					redirect: '/',
					name: 'Laboratory',
					component: {
						render (c) { return c('router-view') }
					},
					children: [
						{
							path: 'staff',
							name: 'Staff',
							component: LabStaff
						},
						{
							path: 'equipment',
				    	        name: 'Equipment',
				    	        component: LabEquipment
				    	    },
				    	    {
				    	    		path: 'supplies',
				    	        name: 'Supplies',
				    	        component: LabSupplies
				    	    }
					]
				},
				{
					path: '/admin',
					redirect: '/admin/summary',
					name: 'Administration',
					component: {
						render (c) { return c('router-view') }
					},
					children: [
						{
							path: 'summary',
							name: 'Summary',
							component: AdminSummary
						},
						{
							path: 'weekly_progress',
							name: 'Weekly progress',
							component: AdminWeeklyProgress
					    },
					    {
					    		path: 'expenses',
					        name: 'Expenses',
					        component: AdminExpenses
					    },
					    {
					    		path: 'funding_accounts',
					        name: 'Funding accounts',
					        component: AdminFundingAccounts
				    	    }
					]
				}
			]
		},
		{
			path: '/choose_lab',
			name: 'Choose Lab',
			component: ChooseLab
		},
		{
			path: '/login',
			name: 'Login',
			component: Login
		},
		{
			path: '/register',
			name: 'Register',
			component: Register
		},
		{
			path: '/pages',
			redirect: '/pages/p404',
			name: 'Pages',
			component: {
				render (c) { return c('router-view') }
			},
			children: [
				{
					path: '404',
					name: 'Page404',
					component: Page404
				},
				{
					path: '500',
					name: 'Page500',
					component: Page500
				}
				]
		}
		]
})
