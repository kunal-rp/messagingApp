import React from 'react';
import { instanceOf } from 'prop-types';
import { withCookies, Cookies } from 'react-cookie';
import {loginWithToken, sampleCall} from '../client';

import { Redirect } from 'react-router-dom'

class ProtectedRoute extends React.Component{

	 static propTypes = {
	    cookies: instanceOf(Cookies).isRequired
	  };


	constructor(props){
		super(props)
		this.state = {
		};
	}

	render() {
		const { cookies } = this.props;

		console.log(cookies.get('jwtToken'))
		if(cookies.get('jwtToken') === undefined){
			return <Redirect to='/login' />
		}

		return this.props.children;
	}

}

export default withCookies(ProtectedRoute);
