/* eslint-disable eqeqeq */
/* eslint-disable react-native/no-inline-styles */
import React, {Component} from 'react';
import {View, Text, StyleSheet} from 'react-native';
import Icon from 'react-native-ionicons';

export default class Cards extends Component {
  render() {
    return (
      <View
        style={{
          ...styles.container,
          backgroundColor: this.props.bg,
        }}>
        <View style={styles.col}>
          <Icon
            name={this.props.icon}
            size={30}
            color={this.props.bg == '#D93B4A' ? '#fff' : 'red'}
          />
        </View>
        <Text style={styles.title}>{this.props.title}</Text>
        <Text
          styles={{
            ...styles.number,
            color: this.props.bg == '#D93B4A' ? '#FFF' : '#000',
          }}>
          {this.props.number}
        </Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    height: 110,
    width: 110,
    borderRadius: 30,
    padding: 15,
    marginHorizontal: 10,
  },
  col: {
    flexDirection: 'row',
  },
  title: {
    marginTop: 15,
    color: '#b8b8aa',
    fontWeight: 'bold',
    flexShrink: 12,
  },
  number: {
    fontWeight: 'bold',
    fontSize: 22,
  },
});
