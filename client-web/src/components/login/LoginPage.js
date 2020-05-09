import React from 'react';
import Grid from '@material-ui/core/Grid';
import SSOLoginComp from './SSOLoginComp'

class LoginPage extends React.Component{

	render() {

		return (
			 
			<div>
			<Grid container spacing={3}>
				<Grid item xs={12}>
				Login to get more access !
				</Grid>
				<Grid item xs={12}>
				<SSOLoginComp/>
				</Grid>
			</Grid>			
			</div>);
	}

	
}

export default LoginPage;
