

import React from 'react';
import { StyleSheet, View ,Text } from 'react-native';


export default class LifecycleComponent extends React.Component{


    static defaultProps = {
        componentName:"LifecycleComponet"
    }

    constructor(props){
        super(props)
        console.log("constructor")
        this.state = {count:0}
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
            <View style={{backgroundColor:'red'}} >
                <Text style={{fontSize:30}} onPress={()=>{
                console.log("点击")
                this.setState({count:this.state.count+1})
            }}>LifecycleComponent</Text>
                <Text style={{fontSize:30}}>点击了{this.state.count}次！</Text>
            </View>
        );
    }



}