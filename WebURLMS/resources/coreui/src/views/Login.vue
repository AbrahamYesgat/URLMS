<template>
  <div class="app flex-row align-items-center">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-md-8">
          <div class="card-group mb-0">
            <div class="card p-4">
              <div class="card-body">
                <h1>Login</h1>
                <p class="text-muted">Sign In to your account</p>
                <b-form>
                <b-form-group>
                <div class="input-group mb-3">
                  <span class="input-group-addon"><i class="icon-user"></i></span>
                  <b-form-input name="email" id="email" type="text" class="form-control" v-model="form.email" v-validate="'email'" :class="{'input': true, 'is-danger': errors.has('email') }" placeholder="Email"></b-form-input>
                  </div>
                  <span class="text-danger" v-if="errors.has('email')">Please enter a valid email</span>
                </b-form-group>
                
                <b-form-group>
                <div class="input-group mb-4">
                  <span class="input-group-addon"><i class="icon-lock"></i></span>
                  <input type="password" class="form-control" v-model="form.password" placeholder="Password">
                  </div>
                </b-form-group>
                <div class="row">
                  <div class="col-6">
                    <button type="button" @click="loginClicked" class="btn btn-primary px-4">Login</button>
                  </div>
                </div>
                <span v-if="loginError" class="text-danger">Bad email/password combination</span>
                </b-form>
              </div>
            </div>
            <div class="card text-white bg-primary py-5 d-md-down-none" style="width:44%">
              <div class="card-body text-center">
                <div>
                  <h2>Sign up</h2>
                  <p>If you are a laboratory director and don't have an account yet, please click the button below. Otherwise, ask your directory to give you your login info to enter your laboratory system.</p>
                  <button type="button" class="btn btn-primary active mt-3" @click="register">Register Now!</button>
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
export default {
  name: 'Login',
  data() {
	  return {
		  loginError: false,
		  form: {
		  	email: '',
		  	password: ''
		  }
	  }
  },
  methods: {
    register (e) {
      e.preventDefault()
      this.$router.push('/register')
    },
    loginClicked() {
    	this.$validator.validateAll();
    	
    	if(!this.errors.any()) {
    	    axios.post('/login', 
    				{email: this.form.email,
    			    password: this.form.password})
    			.then(response => {
    				if(!response.data['status']) {
    					this.loginError = true;
    					this.form.email = '';
    					this.form.password = '';
    				} else {
    					this.$router.push('/choose_lab')
    				}
    			});
    	}
    }
  }
}
</script>
