package entities;

/*
 * Enum for UserTypes that control User permissions to make changes in the web app
 * Admin type: super user with ability to make admin level changes, ie delete/add exercises to db
 * User type: default user upon sign up with access to core application
 */

public enum UserType {
	USER, ADMIN, NONE
}
