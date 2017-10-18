

import React from 'react';
import { StyleSheet, View } from 'react-native';


export default class LifecycleComponent extends React.Component{


    constructor(props){
        super(props)
        console.log("constructor")
    }


    componentWillMount(){
        console.log("componentWillMount")
    }

    componentDidMount(){
        console.log("componentDidMount")
    }

    componentWillReceiveProps(nextProps){
       console.log("componentWillReceiveProps") 
    }

    shouldComponentUpdate(nextProps,nextState){
        console.log("shouldConponentUpdate")
        return true;
    }


    componentWillUpdate(nextProps,nextState){
        console.log("componentWillUpdate")
    }
    componentDidUpdate(nextProps,nextState){
        console.log("componentDidUpdate")
    }


    componentWillUnmount(){
        console.log("componentWillUnmout")
    }

    render(){
        console.log("render")
        return(
            <View>
                LifecycleComponent
            </View>
        );
    }



}