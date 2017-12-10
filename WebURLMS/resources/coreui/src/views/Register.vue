<template>
  <div class="app flex-row align-items-center">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-md-6">
          <div class="card mx-4">
            <div class="card-body p-4">
              <h1>Register</h1>
              <p class="text-muted">Create your account</p>
              <b-form>
              <b-form-group class="row mb-3">
              <div class="input-group">
                <span class="input-group-addon"><i class="icon-user"></i></span>
                <b-form-input type="text" name="name" id="name" class="form-control" v-model="form.name" v-validate="'required|alpha_spaces'" :class="{'input': true, 'is-danger': errors.has('name') }" placeholder="Full name"></b-form-input>
              </div>
              <span class="text-danger" v-if="errors.has('name')">Please enter a valid name</span>
              </b-form-group>

			  <b-form-group class="row mb-3">
              <div class="input-group">
                <span class="input-group-addon">@</span>
                <b-form-input type="text" name="email" id="email" class="form-control" v-model="form.email" v-validate="'required|email'" :class="{'input': true, 'is-danger': errors.has('email') }" placeholder="Email"></b-form-input>
              </div>
              <span class="text-danger" v-if="errors.has('email')">Please enter a valid email</span>
              </b-form-group>

              <b-form-group class="row mb-3">
              <div class="input-group mb-2">
                <span class="input-group-addon"><i class="icon-lock"></i></span>
                <b-form-input type="password" class="form-control" v-model="form.password" placeholder="Password"></b-form-input>
              </div>

              <div class="input-group">
                <span class="input-group-addon"><i class="icon-lock"></i></span>
                <b-form-input type="password" class="form-control" v-model="form.password2" placeholder="Repeat password"></b-form-input>
              </div>
              </b-form-group>

              <button type="button" class="btn btn-block btn-success" @click="registerClicked">Create Account</button>
              <span class="text-danger" v-if="registerError">{{ errorMessage }}</span>
              <div class="col text-center">
                 <button type="button" class="btn btn-link px-0" @click="login">Already have an account?</button>
              </div>
              </b-form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Register',
  data() {
	  return {
		  registerError: false,
		  errorMessage: '',
		  form: {
			  name: '',
			  email: '',
			  password: '',
			  password2: ''
		  }
	  }
  },
  methods: {
	  login(e) {
	      this.$router.push('/login')
	  },
	  registerClicked() {
		  this.$validator.validateAll();
		  
		  if(this.form.password != this.form.password2) {
			  this.registerError = true;
			  this.errorMessage = 'Passwords don\'t match';
			  this.form.password = '';
			  this.form.password2 = '';
		  }
		  
		  else if(!this.errors.any()) {
			  axios.post('/register', 
	    				{name: this.form.name,
				     email: this.form.email,
	    			     password: this.form.password})
	    			.then(response => {
	    				if(!response.data['status']) {
	    					this.registerError = true;
	    					this.errorMessage = response.data['message'];
	    					this.form.name = '';
	    					this.form.email = '';
	    					this.form.password = '';
	    					this.form.password2 = '';
	    				} else {
	    					this.$router.push('/choose_lab')
	    				}
	    			});
		  }
	  }
  }
}
</script>
