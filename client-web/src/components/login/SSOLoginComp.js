import React from 'react';
import {GoogleLogin,GoogleLogout} from 'react-google-login';
import Grid from '@material-ui/core/Grid';
import Button from '@material-ui/core/Button';
import { instanceOf } from 'prop-types';
import { withCookies, Cookies } from 'react-cookie';
import {loginWithToken, sampleCall} from '../../client';
import { Redirect } from 'react-router-dom'

class SSOLoginComp extends React.Component{

	 static propTypes = {
	    cookies: instanceOf(Cookies).isRequired
	  };


	constructor(props){
		const { cookies } = props;
		super(props)
		this.state = {
			loggedIn: cookies.get('jwtToken') !== undefined,
		};
	}

	render() {

		if(this.state.loggedIn){
			return <Redirect to='/dash' />
		}

		return (
			<div>
			<Grid container spacing={3}>
				<Grid item xs={12}>
					<GoogleLogin
						clientId="436041114033-g9veoeqo9cvqlvia27t30oibijah35mf.apps.googleusercontent.com"
						buttonText="Login with Google"
						onSuccess={res => this.loginWithGoogle(res)}
						/>
				</Grid>
			</Grid>			
			</div>);
	}

	loginWithGoogle(res) {
		const { cookies } = this.props;
		loginWithToken(res.tokenId, (token) =>{
			cookies.set('jwtToken', token, { path: '/' , });
			window.location.reload(false);
		})
	}
}

export default withCookies(SSOLoginComp);
