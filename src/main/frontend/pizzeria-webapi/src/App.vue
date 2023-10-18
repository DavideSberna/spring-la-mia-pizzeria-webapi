<template>
  <main>
   <p>ciao</p>
  </main>
</template>

<script>
import axios from 'axios';
export default {
	name: 'App',
	
	
	methods: {
		prova() {
             axios.get("http://localhost:8080/api/v1.0/pizza").then((res) => {
		         console.log(res.data)
		      }).catch((error) => {
		         console.log(error)
		      }).finally(() => {
		         console.log("finally OK")
		      });
		    
        }
	},
	mounted() {
        this.prova();
    }
}

</script>
