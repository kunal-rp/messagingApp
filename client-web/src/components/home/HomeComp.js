import React from 'react';
import Grid from '@material-ui/core/Grid';
import Button from '@material-ui/core/Button';
import {HOME_SUBTITLE} from '../../utils/Messages'

class HomeComp extends React.Component{

	constructor(props){
		super(props)
	}

	render() {
		return (
			<div>
			<Grid container spacing={1}>
				<Grid item xs={12}>
					{HOME_SUBTITLE}
				</Grid>
			</Grid>			
			</div>);
	}

}

export default HomeComp;
