module.exports = function(grunt) {

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        clean: [ 'templates '],
        less: {
            'templates/default/css/iron-admin.css': 'src/default/less/iron-admin.less'
        },
        copy: {
            main: {
                cwd: 'src/',
                expand: true,
                src: [ '**/css/*', '**/html/*' ],
                dest: 'templates/'
            }
        },
        watch: {
            files: [
                'src/**/*.html', 'src/**/*.css', 'src/**/*.js'
            ],
            tasks: [ 'clean', 'less', 'copy' ]
        }
    });

    grunt.loadNpmTasks('grunt-contrib-less');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-contrib-watch');

    grunt.registerTask('default', ['clean', 'less', 'copy']);

};
